# SPRINT 2 TASK PACKAGES (Iteration 2)

## T3 [Backend] - Stage Template Pack

### Output
- `StageProfile` 5종:
  - `Survival`
  - `Sanitation`
  - `Trade`
  - `Explore`
  - `Choice`
- 각 스테이지 공통 필드:
  - `riskBase`, `rewardBase`, `toleranceMin`, `corruptionGain`, `narrativeHintId`

### Acceptance
- 각 템플릿은 동일 seed에서 형태 변화가 제한된 규칙 기반 생성
- 실패 조건이 발생해도 다음 런 seed는 안정 유지

## T8 [Backend] - Event Module Pack

### Output
- 사건 카탈로그:
  - 오염 증거
  - 항체 대가
  - 진실 거래
  - 선택 기록
- 사건 바인딩 규칙:
  - `tags`와 `factionFlags`에 따라 가중치 적용

### Acceptance
- 사건 중복은 최소화되며, 동일 조합 연속 출현률 제한
- 라네이레/에이전트용 해석 트리거로 최소 1회 이상 연결

## T9 [Backend] - Evidence Engine Pack

### Output
- 사건 결과→증거 변환 규칙:
  - 증거 ID
  - 증거 카테고리
  - 신뢰도
  - 연동 NPC
  - 플롯 키워드

### Acceptance
- 증거 보드/연대기에서 동일 출처 중복 2건 이내
- 증거 누적 시 라네이레 해석 단계 갱신

## T6 [Planner] - Lairneyre Update Pack

### Output
- 해석 단계 임계값:
  - 1차: `증거 1~2`
  - 2차: `증거 3~5`
  - 3차: `증거 6+`
- 메시지 스타일:
  - 경고형 → 비용형 → 구조형

### Acceptance
- 플레이어 행위가 메시지 스타일을 바꾸는지 검증 가능
- 과잉 진실/과소 진실 상태에서 분기 반응 차이가 발생

## T7 [Planner] - Ending Branch Pack

### Output
- 4개 엔딩 브랜치 정의:
  - 보존형
  - 제어형
  - 재작성형
  - 균열수용형
- 분기 조건 표(증거량/신뢰도/시즌상태)

### Acceptance
- 적어도 2개 브랜치는 상반된 세계 결과를 생산
- 각 분기 최소 3개 선택 전후단계 필요

## T14 [Frontend] - Forecast & HUD Pack

### Output
- Season Forecast 컴포넌트:
  - 예보 확률
  - 신뢰도
  - 리스크 플래그
- HUD 정합성 규칙:
  - 오염/정화/진실을 1패널에 통합

### Acceptance
- 연계 데이터 결여 시 기본값 표시
- 시즌 전환 직전 안내 텍스트 표시

## T15 [Frontend] - Board + Dashboard Pack

### Output
- 증거 탐색/거래/정비 UI 라벨
- 모바일 터치 최소 동선 설계

### Acceptance
- 오차 없는 상태 토글(오염/정화/신뢰)
- 스와이프만으로 핵심 보드 이동 가능

## S3 [QE] - Weight Reflection Pack

### Output
- 3런 연속 실행 로그 템플릿
- 선택→이벤트 가중치 반영 추적표

### Acceptance
- 가중치 반영 전/후 변화율이 5% 이상 차이
- 재현성: 3회 반복에서 패턴 정합성 확인

## S4 [QE] - Season Transition Pack

### Output
- 시즌 전환 직후 환경셋 체크리스트
- 몬스터·증거셋 교체 시나리오 로그

### Acceptance
- 변경 전후 이벤트셋 차이 검증
- 미변경 항목 비율 상한 정의 및 점검
