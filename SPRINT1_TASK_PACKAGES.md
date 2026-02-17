# SPRINT 1 TASK PACKAGES (Execution Artifact)

## T1 [Backend] - Run Engine Interface Pack

### Output
- `run-engine-spec.yaml` (문서형, 생성 예정)
- Required fields:
  - `runId: string`
  - `seed: integer`
  - `party: CharacterRef[]`
  - `loadouts: ItemRef[]`
  - `riskMod: number`
  - `objectiveFlags: string[]`
  - `currentDepth: number`
  - `stateVersion: string`
  - `createdAt: ISO8601`

### Acceptance
- `createRun`가 동일 입력 시 동일 seed에서 동일 구조 생성
- `tickRun`은 이벤트 1개 이상 또는 종료 이벤트 반환
- `exitRun`은 Chronicle 최소 5개 항목 생성
- `resumeRun`은 종료되지 않은 runId 복구 가능

## T2 [Backend] - Risk Stack Harmonization Pack

### Output
- 수치 규칙표(문서):
  - `AetherExposure`(0~100), `MeseRaPressure`(0~100), `CorruptionRate`, `PurificationLoad`
  - 감쇠/가속 상수:
    - 혈석 보정: `corruptionRate * 0.65`, `resourceDebt +1`
    - 성석 보정: `magnitude + regionCleanseCost`

### Acceptance
- 감염 증가가 존재하는 반면, 장기적 메세라 압력 누적도 추적되는지 증명
- 동일 런 내에서 수치 변경 로그 일관성 유지

## T4 [Planner] - Act Transition Pack

### Output
- Act 전이 표:
  - Act0 완료 조건
  - Act1 진입 조건
  - 실패/되돌림 조건
- 메시지 라인 템플릿(최소 5줄):
  - 숲은 위협인가
  - 숲을 살리면 도시가 무너지는가

### Acceptance
- 플레이어 선택 1회 이상으로 주 질문이 전개되어야 함
- 실패 경로에서 무한 루프 방지(재진입 조건 존재)

## T5 [Planner] - NPC Arc Pack

### Output
- 상태 전환표 (5개 NPC 상태)
- 대사 키워드 세트
  - 라네이레: 비용 계산형 대사
  - 세비리스: 가격표형 거래 대사

### Acceptance
- 증거량 누적에 따라 최소 1회 이상 상태 전환
- 충돌(동일 상황)에서 대사 패턴이 일정하지 않게 분기

## T12 [Frontend] - Chronicle UI Pack

### Output
- 5개 요약 슬롯:
  - 생존 지표
  - 위험 변화
  - 행동 선택
  - 손실 기록
  - 증거 기록
- 컴포넌트 규격:
  - 제목 1줄
  - 아이콘 2개
  - 터치 링크 3개

### Acceptance
- 런 종료 1회당 요약 노출 실패율 0%
- 모바일 1회 터치로 연대기 탭 진입 가능

## T13 [Frontend] - Proof Board Pack

### Output
- 필터 항목: 오염/항체/거래/결정/진실
- 항목 셀 정보:
  - 카테고리
  - 획득 시점
  - 지역/출처
  - 연계 NPC

### Acceptance
- 증거 클릭 시 메인 플롯 연동 라벨 표시
- 같은 증거 소스 중복 표시 1건 이하

## S1 [QE] - Early Run Validation Pack

### Output
- 수동 체크리스트 (20분 시나리오)
  - 생존, 귀환, 오염 경고 인지, 연대기 확인
- 실패 로그 규격:
  - Step / Expected / Observed / Severity

### Acceptance
- 핵심 시나리오 각 항목 누락 없이 추적 가능
- blocker 기준(치명도 높음 이상) 1건 이상 시 바로 조치

## S2 [QE] - Evidence Split Pack

### Output
- 증거별 라네이레 상태 반응 추적표
- 동일 증거 반복 수집 시 상태 전이 확인표

### Acceptance
- 동일 증거 라운드에서 최소 1개 이상의 상태 변화 보장
- 증거 수집 누락 시 분기 미달 0건 허용
