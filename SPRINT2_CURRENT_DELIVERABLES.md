# SPRINT 2 CURRENT DELIVERABLES (First Draft)

## Backend: T3/T8/T9

### T3 5-Stage Template Core (Draft)
- `Survival`
  - 목적: 초반 회복/생존 탐색
  - 난도: 낮음
  - 오염 성장: 느림
- `Sanitation`
  - 목적: 정화·위험 압박
  - 보상: 중간
  - 오염 성장: 빠름
- `Trade`
  - 목적: 정보/아이템 교환
  - 위기: 중간
  - 동맹/배신 이벤트 가중치 상승
- `Explore`
  - 목적: 증거 수집 및 분기 확장
  - 위험: 높음
  - 이벤트 가중치: 탐사형 증가
- `Choice`
  - 목적: 2개 이상 결과 분기
  - 위험: 매우 높음
  - 핵심 리스크: 후속 가중치 왜곡

### T8 Event Module Draft
- 사건 ID 형식: `evt_<도메인>_<난도>`
- 예시:
  - `evt_contagion_01`
  - `evt_serum_cost_02`
  - `evt_truth_broker_03`
- 분배 규칙:
  - `factionAffinity`에 따른 최소1회 보정
  - `evidenceWeight`로 동일 패턴 연속 억제

### T9 Evidence Engine Draft
- 증거 상태:
  - `Unverified`
  - `Tagged`
  - `Correlated`
  - `Interpretable`
- 변환 규칙:
  - 사건 점수 >= threshold면 `Tagged`
  - 같은 지역 연속 수집 시 `Correlated` 자동 업그레이드

## Planner: T6/T7

### T6 Lairneyre Update Draft
- 임계값:
  - 0~2개: 경고성 대사
  - 3~5개: 비용형 대사
  - 6개 이상: 구조형 대사
- 대사 템플릿:
  - 경고성: “항체를 자주 쓰면, 미래 비용이 커져.”
  - 비용형: “지금은 버텨도, 다음 폭풍이 더 길어져.”
  - 구조형: “숫자보다 구조를 바꿔야 한다.”

### T7 Ending Draft
- 보존형: 숲 통제 실패 확률 하향, 문명 의존성 유지
- 제어형: 강경 통제형, 단기 생존 상승/장기 구조 붕괴
- 재작성형: 계약 재정의, 시즌 변동 수치 안정
- 균열수용형: 불완전 공존, 시즌 장기 변동 허용

## Frontend: T14/T15

### T14 Forecast + HUD Draft
- Forecast 표기:
  - 예보 상태: `안정`, `불안`, `격변`
  - 신뢰도: 3단계 바 코드
  - 위험표지: 색상 3단계
- HUD:
  - 핵심 수치 3개 (에테르, 메세라, 신뢰도)
  - 1차 경고, 2차 액션 제안, 3차 전환 라인

### T15 Proof/Board UX Draft
- 필터 우선순위: `오염` → `항체` → `거래` → `증거`
- 모바일 행동:
  - 단일 탭으로 요약 토글
  - 길게 누르기: 상세 확장

## QE/Validation: S3/S4

### S3 Draft
- 로그 필드:
  - runIndex
  - selectedChoice
  - weightedEventDelta
- 목표: 3런간 가중치 누적값의 차이 추적

### S4 Draft
- 계절 전환 체크:
  - 몬스터 타입 치환
  - 보드 아이콘 집합 변경
  - 이벤트 배포 밀도 증감
