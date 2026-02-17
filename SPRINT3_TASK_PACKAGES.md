# SPRINT 3 TASK PACKAGES (Iteration 3)

## T10 [Backend] - Run History Weight Pack

### Output
- 이벤트 가중치 규칙:
  - `choiceCategory`별 가중치 집계
  - 직전 3런 선택 히스토리 기반 누적치 반영
  - 하한/상한 보정치 적용
- 데이터 키:
  - `lastChoices`
  - `historyPressure`
  - `biasDrift`

### Acceptance
- 같은 선택이 과도하게 누적되지 않도록 반감 구간 존재
- 시즌 전환 시 가중치 리셋 규칙이 명확히 문서화

## T11 [Backend] - Season Affix Pack

### Output
- 시즌별 페널티/버프 테이블:
  - 에테르 변형별 위험도
  - 메세라 반응 민감도
  - 정화/거래 난이도 조정
- 지역/캐릭터 상호작용 보정

### Acceptance
- 시즌 변경 시 예측 불가한 급변 대신 단계적 변화
- 동맹/적대 패턴과 충돌하지 않음

## T16 [Backend] - Season State Machine Pack

### Output
- 상태:
  - `OPENING` (개막)
  - `EXPANSION` (확산)
  - `RESOLUTION` (결산)
  - `STABLE` (안정)
- 전환 트리거:
  - 이벤트 횟수
  - 플레이어 참여 임계치
  - 시즌 보스 진척률

### Acceptance
- 순환형 전환(안정→개막) 가능
- 잘못된 상태 조합 방지

## T17 [Backend] - Variant Terrain Pack

### Output
- 변형 세트:
  - 몬스터 군집 구성
  - 타일셋 변형 플래그
  - 상태이상 목록
- 네피아 층별 우선 적용 규칙

### Acceptance
- 동일 시즌 내 과도 반복 완화 장치 포함
- 시즌 간 충돌 최소화

## T18 [Backend] - Season Boss Pack

### Output
- 보스 모드:
  - 정화형
  - 협력형
  - 형식형/의식형
- 보스 실패·성공 조건 분기

### Acceptance
- 보스 상호작용이 메인 플롯 분기와 연결
- 보스 결과가 다음 시즌 상태에 반영

## S5 [QE] - Multi-End Coverage Pack

### Output
- 엔드 분기 최소 4개 도달성 체크 시트
- 런-시즌 상호작용 회귀 추적 항목

### Acceptance
- 동일 조건 재실행에서 분기 중복 오류 감지
- 최소 1개 엔딩이 데이터 부족으로 차단되지 않음

## Validator [Ops Alignment]

### Output
- 운영 체크리스트 v1:
  - 72h 무리스크 배포 조건
  - 롤백 트리거 지표
  - 시즌 전환 알림 정책

### Acceptance
- 문서 기반 운영 기준이 S3 테스트 케이스와 교차 참조
- 장애 탐지 지표가 주 단위 모니터링에 매핑 가능
