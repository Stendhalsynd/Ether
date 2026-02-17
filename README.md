# Ether — 에테르의 눈 PoC (Android)

`에테르의 눈` 프로젝트의 Android PoC입니다.  
현재는 **탐사/연대기/요약 탭 + 내비게이션 + 이벤트 기록 흐름**이 동작하는 최소 실행 버전입니다.

## 저장소 목적
- 게임 기획서를 기반으로 한 모바일 퍼즐형 로그라이크 RPG 프로토타입의 Android 기반 PoC 배포
- 런(탐사)에서 생성된 사건을 연대기와 상세 화면으로 이어주는 내비게이션 구조 검증
- 향후 백엔드 API/세이브/시즌 룹 연동을 위한 UI/상태 골격 확보

## 구현 완료 항목
- Android Studio 실행 가능한 Compose 프로젝트 구성
- 바텀 탭 네비게이션(탐사, 연대기, 요약)
- 탐사 화면 동작
  - 런 1턴 진행
  - 긴급 정화
  - 리셋
  - 최근 이벤트 표시
- 연대기 기능
  - 로그 누적 목록
  - 목록 항목 클릭 시 상세 화면 이동
- 상세 화면
  - 이벤트 상세 정보 표시
  - 연대기로 복귀
  - 상단 뒤로 가기 액션 지원

## 프로젝트 구성
- `app/` Android 애플리케이션 모듈
- `app/src/main/java/com/example/etherpoc/MainActivity.kt` : PoC 화면/상태/네비게이션
- `app/build.gradle.kts` : Android + Compose + Navigation 의존성
- `POC_ANDROID_STUDIO_SETUP.md` : 설치 가이드
- `SWARM_ASSIGNMENTS.md`, `PLAN.md`, `TASK.md`, `SPEC.md` : 기획/작업 산출물

## 실행 방법
### 1) Android Studio에서 열기
1. `File > Open` → `/Users/jihun/StudioProjects/Ether`
2. Gradle Sync 완료
3. `Run > Run 'app'`

### 2) 로컬 Gradle 빌드
```bash
cd /Users/jihun/StudioProjects/Ether
# local.properties 에 본인 Android SDK 경로 설정
echo "sdk.dir=/Users/본인계정/Library/Android/sdk" > local.properties
./gradlew :app:assembleDebug
```

### 3) APK 설치
산출물: `app/build/outputs/apk/debug/app-debug.apk`

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## 설치 후 확인 체크리스트
- 바텀 탭 전환(탐사/연대기/요약)이 정상 동작
- 탐사에서 이벤트 생성 → 연대기에 반영
- 연대기 항목 클릭 → 상세 화면 이동
- 상세 화면에서 뒤로가기 동작

## 다음 확장 계획(요약)
- 탐사/이벤트 로직을 실제 서버 이벤트 큐와 동기화
- 시즌/세비리스 이벤트 보드, NPC 상호작용 UI 연동
- 저장/로드 및 계정 연동
