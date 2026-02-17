package com.example.etherpoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.util.Locale
import kotlin.random.Random

private data class EventCard(
    val title: String,
    val desc: String,
    val corruption: Int,
    val antitoxin: Int,
    val proof: Int,
)

private data class RunLog(
    val runId: Int,
    val title: String,
    val desc: String,
    val corruption: Int,
    val antitoxin: Int,
    val proof: Int,
    val source: String,
)

private data class GameState(
    val run: Int = 0,
    val corruption: Int = 0,
    val antitoxin: Int = 0,
    val proofs: Int = 0,
)

private enum class TopTab(val route: String, val label: String, val title: String) {
    Run("run", "탐사", "탐사 대시보드"),
    Chronicle("chronicle", "연대기", "연대기"),
    Report("report", "요약", "운영 리포트")
}

private const val ROUTE_RUN_DETAIL = "runDetail"

private fun iconText(screen: TopTab): String = when (screen) {
    TopTab.Run -> "🧭"
    TopTab.Chronicle -> "📜"
    TopTab.Report -> "📑"
}

private fun resolveCurrentTab(route: String?): TopTab = when (route) {
    TopTab.Run.route -> TopTab.Run
    TopTab.Chronicle.route -> TopTab.Chronicle
    TopTab.Report.route -> TopTab.Report
    else -> TopTab.Run
}

private fun clamp(value: Int, min: Int = 0, max: Int = 100): Int = value.coerceIn(min, max)

private fun nextEvent(day: Int, seed: Int): EventCard {
    val table = listOf(
        EventCard(
            "이형의 숲 가장자리",
            "희미한 안개가 내려앉고, 오염의 균열에서 유물 조각이 떨어진다.",
            6,
            3,
            2,
        ),
        EventCard(
            "델피의 그림자",
            "정보가 거래되고 있다. 세비리스는 대가를 요구한다.",
            4,
            1,
            3,
        ),
        EventCard(
            "레시마스 하층",
            "급격한 에테르 폭풍으로 이동 속도가 급락한다.",
            10,
            4,
            1,
        ),
        EventCard(
            "남쪽 폐허 지대",
            "메세라 표본실험 로그를 찾아 긴급 정화 결정을 내려야 한다.",
            7,
            5,
            2,
        ),
    )

    val event = table[Random(seed + day * 17).nextInt(table.size)]
    return when (day % 7) {
        0 -> event.copy(desc = "[예고] 시즌 풍향이 바뀌었다. 다음 2턴 내 위험이 커질 수 있다.\n" + event.desc)
        3 -> event.copy(desc = "[NPC] 라네이레가 단서를 전달한다. 해석 비용이 증가한다.\n" + event.desc)
        else -> event
    }
}

private fun runTurn(state: GameState, turn: Int): Pair<GameState, EventCard> {
    val e = nextEvent(turn, state.run + state.corruption)
    val next = state.copy(
        run = state.run + 1,
        corruption = clamp(state.corruption + e.corruption - if (state.antitoxin > 45) 3 else 0),
        antitoxin = clamp(state.antitoxin + e.antitoxin),
        proofs = clamp(state.proofs + e.proof, max = 9999),
    )
    return Pair(next, e)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppRoot() }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppRoot() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val currentTab = resolveCurrentTab(currentRoute)
    val isTopLevel = currentRoute == TopTab.Run.route
        || currentRoute == TopTab.Chronicle.route
        || currentRoute == TopTab.Report.route
    val currentTitle = if (currentRoute?.startsWith("$ROUTE_RUN_DETAIL/") == true) {
        "이벤트 상세"
    } else if (isTopLevel) {
        when (currentTab) {
            TopTab.Run -> "탐사 대시보드"
            TopTab.Chronicle -> "연대기"
            TopTab.Report -> "운영 리포트"
        }
    } else {
        "이벤트 상세"
    }

    var state by remember { mutableStateOf(GameState()) }
    var turn by remember { mutableIntStateOf(1) }
    val history = remember { mutableStateListOf<RunLog>() }
    var lastEvent by remember { mutableStateOf<EventCard?>(null) }

    val phaseText = if (state.corruption >= 80) {
        "위험: 오염 임계치 근접"
    } else {
        "현재 안정"
    }

    fun navigateTop(tab: TopTab) {
        navController.navigate(tab.route) {
            launchSingleTop = true
        }
    }

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(currentTitle) },
                    navigationIcon = {
                        if (!isTopLevel) {
                            TextButton(onClick = { navController.popBackStack() }) {
                                Text("‹ 뒤로", color = Color.White)
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF1E1F2A),
                        titleContentColor = Color.White,
                    ),
                )
            },
            bottomBar = {
                if (isTopLevel) {
                    NavigationBar {
                        TopTab.values().forEach { tab ->
                            NavigationBarItem(
                                selected = currentTab == tab,
                                onClick = { navigateTop(tab) },
                                label = { Text(tab.label) },
                                icon = { Text(iconText(tab)) },
                            )
                        }
                    }
                }
            }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = TopTab.Run.route,
                modifier = Modifier.padding(padding),
            ) {
                composable(TopTab.Run.route) {
                    RunScreen(
                        modifier = Modifier.fillMaxSize(),
                        state = state,
                        phaseText = phaseText,
                        lastEvent = lastEvent,
                        history = history,
                        onRun = {
                            val (nextState, event) = runTurn(state, turn)
                            state = nextState
                            turn += 1
                            lastEvent = event
                            history.add(
                                0,
                                RunLog(
                                    runId = nextState.run,
                                    title = event.title,
                                    desc = event.desc,
                                    corruption = event.corruption,
                                    antitoxin = event.antitoxin,
                                    proof = event.proof,
                                    source = "탐사",
                                ),
                            )
                        },
                        onClean = {
                            state = state.copy(
                                antitoxin = clamp(state.antitoxin - 5, min = 0),
                                corruption = clamp(state.corruption - 12),
                            )
                            history.add(
                                0,
                                RunLog(
                                    runId = state.run,
                                    title = "긴급 정화",
                                    desc = "정화제를 사용해 오염도를 낮췄다. 단기 완화 대신 장기 회복이 늦어질 수 있다.",
                                    corruption = -12,
                                    antitoxin = -5,
                                    proof = 0,
                                    source = "생존 행위",
                                ),
                            )
                        },
                        onReset = {
                            state = GameState()
                            turn = 1
                            history.clear()
                            lastEvent = null
                        },
                        onOpenRecentLog = { runId ->
                            navController.navigate("$ROUTE_RUN_DETAIL/$runId")
                        },
                    )
                }

                composable(TopTab.Chronicle.route) {
                    ChronicleScreen(
                        modifier = Modifier.fillMaxSize(),
                        history = history,
                        state = state,
                        onOpenLog = { runId ->
                            navController.navigate("$ROUTE_RUN_DETAIL/$runId")
                        },
                    )
                }

                composable(TopTab.Report.route) {
                    ReportScreen(
                        modifier = Modifier.fillMaxSize(),
                        state = state,
                    )
                }

                composable(
                    route = "$ROUTE_RUN_DETAIL/{runId}",
                    arguments = listOf(navArgument("runId") { type = NavType.IntType }),
                ) {
                    val runId = it.arguments?.getInt("runId") ?: 0
                    val log = history.firstOrNull { entry -> entry.runId == runId }
                    RunDetailScreen(
                        modifier = Modifier.fillMaxSize(),
                        log = log,
                        onOpenChronicle = { navigateTop(TopTab.Chronicle) },
                    )
                }
            }
        }
    }
}

@Composable
private fun RunScreen(
    modifier: Modifier,
    state: GameState,
    phaseText: String,
    lastEvent: EventCard?,
    history: List<RunLog>,
    onRun: () -> Unit,
    onClean: () -> Unit,
    onReset: () -> Unit,
    onOpenRecentLog: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        StatCards(state)
        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = onRun) {
                Text("런 1턴 진행")
            }
            ElevatedButton(onClick = onClean) {
                Text("긴급 정화")
            }
            TextButton(onClick = onReset) {
                Text("리셋")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        AssistChip(
            onClick = {},
            enabled = false,
            label = { Text(phaseText) },
        )

        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1F2A))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "최근 이벤트",
                    color = Color(0xFF7DFFDA),
                    fontWeight = FontWeight.SemiBold,
                )
                if (lastEvent == null) {
                    Text("런을 시작하면 이벤트 로그가 표시됩니다.")
                } else {
                    Text(lastEvent.title, fontWeight = FontWeight.Bold)
                    Text(lastEvent.desc)
                    Text("오염:+${lastEvent.corruption} / 항체:+${lastEvent.antitoxin} / 증거:+${lastEvent.proof}")
                }
            }
        }

        if (lastEvent != null && history.isNotEmpty()) {
            TextButton(
                onClick = { onOpenRecentLog(history.first().runId) },
                modifier = Modifier.align(Alignment.End),
            ) {
                Text("최근 로그 상세")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Text("최근 4개 로그", style = MaterialTheme.typography.titleMedium)
        Text("상단 탐사에서 생성된 연대기입니다.", fontSize = 12.sp)

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            contentPadding = PaddingValues(vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(history.take(4)) { entry ->
                Text("• [${entry.source}] ${entry.title}")
            }
        }
    }
}

@Composable
private fun ChronicleScreen(
    modifier: Modifier,
    history: List<RunLog>,
    state: GameState,
    onOpenLog: (Int) -> Unit,
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(12.dp)) {
        Text("연대기 모음", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1F2A))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("현재 수집 지표", fontWeight = FontWeight.SemiBold)
                Text("런 #${state.run}")
                Text("오염 ${state.corruption}")
                Text("항체 ${state.antitoxin}")
                Text("증거 ${state.proofs}")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))

        Text("연대기 항목", style = MaterialTheme.typography.titleMedium)
        if (history.isEmpty()) {
            Text("아직 기록이 없습니다.")
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
                contentPadding = PaddingValues(vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                items(history, key = { it.runId }) { entry ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onOpenLog(entry.runId) },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF212A42)),
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text("RUN #${entry.runId} - ${entry.source}")
                            Text(entry.title)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ReportScreen(modifier: Modifier, state: GameState) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(12.dp)) {
        Text("운영 리포트", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1F2A))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("현재 상태 분석", fontWeight = FontWeight.SemiBold)
                Text("오염율: ${state.corruption}%")
                Text("안정 추정치: ${state.antitoxin}%")
                Text("증거량: ${state.proofs}")
                Text("권고: ")
                Text("- 오염이 80 이상이면 긴급 정화를 먼저 고려")
                Text("- 탐사 중 10단위 로그는 다음 분기 트리거로 자동 반영")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text("시나리오 노트", style = MaterialTheme.typography.titleMedium)
        Text("탐사/연대기/요약은 서로 분리된 탭으로 구성되어 확인이 빠릅니다.")
        Spacer(modifier = Modifier.height(6.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF262B3A))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("다음 단계 준비")
                Text("1) 증거 누적량에 따라 NPC 신뢰도 반영")
                Text("2) 시즌 카드 전환 전 백엔드 이벤트 큐 동기화")
                Text("3) 연동 API와 로그 스키마 정합성 체크")
            }
        }
    }
}

@Composable
private fun RunDetailScreen(
    modifier: Modifier,
    log: RunLog?,
    onOpenChronicle: () -> Unit,
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(12.dp)) {
        Text("이벤트 상세", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(10.dp))

        if (log == null) {
            Text("해당 로그를 찾을 수 없습니다.")
            Spacer(modifier = Modifier.height(10.dp))
            TextButton(onClick = onOpenChronicle) {
                Text("연대기로 복귀")
            }
            return@Column
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1F2A)),
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("RUN #${log.runId} - ${log.source}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(log.title)
                Spacer(modifier = Modifier.height(6.dp))
                Text(log.desc)
                Spacer(modifier = Modifier.height(8.dp))
                Text("변화: 오염 ${log.corruption}, 항체 ${log.antitoxin}, 증거 ${log.proof}")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onOpenChronicle) {
            Text("연대기 홈")
        }
    }
}

@Composable
private fun StatCards(state: GameState) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        InfoPill("런 횟수", (state.run).toString())
        InfoPill("오염", "%03d".format(Locale.getDefault(), state.corruption))
        InfoPill("항체", "%03d".format(Locale.getDefault(), state.antitoxin))
        InfoPill("증거", state.proofs.toString())
    }
}

@Composable
private fun InfoPill(label: String, value: String) {
    Card(
        modifier = Modifier.width(90.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF212A42)),
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF212A42))
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(label, fontSize = 11.sp, color = Color.LightGray)
            Text(value, style = MaterialTheme.typography.titleMedium, color = Color.White)
        }
    }
}
