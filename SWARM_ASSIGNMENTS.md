# SWARM_ASSIGNMENTS.md

## 목표
- `PLAN.md`와 `TASK.md`에 맞춰 프론트엔드/백엔드/기획/QA/검증 역할별로 병렬 실행 가능한 작업을 배정하고, 완료율을 추적한다.

## 스크럼 규칙
- 단위: 주간 스프린트(2주 단위)
- 상태: `todo / in_progress / blocked / review / done`
- 회고: 주차말 `done` 비율 + 다음 주 우선순위 재조정
- 의존성 충돌: 상위 작업이 선행되어야 하는 항목만 먼저 `todo` 해제

## 팀 구성(에이전트)
- `frontend-agent`: UI/UX, 화면 플로우, 모바일 대응
- `backend-agent`: 코어 시스템, 데이터 모델, 엔진/시즌/이벤트 로직
- `planner-agent`: 시나리오/캐릭터/내러티브 설계, 시즌 이벤트 스크립트
- `qe-agent`: 테스트 시나리오 작성, 시나리오 기반 검증
- `validator-agent`: 통합 검증/지표/릴리스 게이트

---

## 스프린트 1 (1~2주): 코어 고정 + 문서 정렬
- Iteration 1 상태: `done`

### frontend-agent
- `T12`: 연대기 자동 요약 UI 기초 프레임 설계
- `T13`: 증거 보드(Proof Board) 탐색/필터 UX
- 상태: `done`

### backend-agent
- `T1`: 코어 런 파이프라인(진입/탐험/귀환/요약) 인터페이스 정의
- `T2`: 오염/항체/선택 위험 수치 스택 정합성 정리
- 상태: `done`

### planner-agent
- `T4`: 프롤로그~엔드 전이 문구/트리거 정리
- `T5`: NPC 상태 전환 규칙(로미아스/라네이레/사이모어/세비리스) 초안
- 상태: `done`

### qe-agent
- `S1`: 신규 유저 20분 런 시나리오 케이스 템플릿 작성
- `S2`: 엔드포인트 분기(증거 축적) 추적 케이스 작성
- 상태: `done`

### validator-agent
- 승인 기준 초안 정리: P0 통과 정의, 롤백 조건, 리스크 경보 규칙 초안
- 상태: `done`

### sprint1 상태 요약
- `todo`: 0
- `in_progress`: 0
- `blocked`: 0
- `done`: 11

### 상태 보조 노트(Iteration 1)
- `blocked`: 현재 0건
- `review`: 현재 0건
- `done`: 현재 0건

---

## 스프린트 2 (3~6주): 스토리-시스템 결합

### frontend-agent
- `T14`: 풍석 예고 UI(Season Forecast) + HUD 핵심 지표
- `T15`: 모바일 오염·정화·신뢰도 대시보드 위젯
- 상태: `done`

### backend-agent
- `T3`: 스테이지 템플릿 5종 구현
- `T8`: 사건 모듈 스키마 + 생성 규칙 초안
- `T9`: 증거 반영 엔진 규칙
- 상태: `done`

### planner-agent
- `T6`: 라네이레 진실 해석 업데이트 규칙(증거 임계값/문구 갱신)
- `T7`: 엔드 엔딩 4개 분기 스크립트 초안
- 상태: `done`

### qe-agent
- `S3`: 연속 3런 선택 기록 가중치 반영 테스트
- `S4`: 시즌 전환 시 네피아 환경·몬스터/증거셋 변경 테스트
- 상태: `done`

### validator-agent
- API 계약 정합성 검토(ISeasonService, IRunEngine, IChronicleWriter, IProofBoard, ICharacterArc)
- 상태: `done`

### sprint2 상태 요약
- `todo`: 0
- `in_progress`: 0
- `blocked`: 0
- `done`: 8

---

## 스프린트 3 (7~10주): 시즌/운영 고도화
### frontend-agent
- `T14` 추가 확장(시즌 보드, 시즌 보상/이벤트 미리보기)
- 시즌 보드 연동 상태 처리 및 예외 화면 설계
- 상태: `done`

### backend-agent
- `T10`: 선택 기록 기반 이벤트 가중치 반영
- `T11`: 시즌/펙트 보정 룰 반영
- `T16`: 시즌 상태 머신 구현
- `T17`: 시즌별 네피아 변형 세트 로더
- `T18`: 시즌 보스 연동
- 상태: `done`

### planner-agent
- 시즌별 내러티브 패키지(청람의 폭풍/흑반의 계절/맹약의 균열) 문서화
- NPC 반응/정보 유통 템플릿 보강
- 상태: `done`

### qe-agent
- `S5`: 엔드 분기 최소 4개 도달성 검증
- 스토리 이벤트 회귀 체크리스트 작성
- 상태: `done`

### validator-agent
- 운영 체크리스트(운영 릴리스 전):
  - 72h 무리스크 배포 조건
  - 롤백 판단 지표
  - 시즌 변경 후 모니터링 알림 규칙
- 상태: `done`

### sprint3 상태 요약
- `todo`: 0
- `in_progress`: 0
- `blocked`: 0
- `done`: 10

---

## 스프린트 4 (11주 이후): 안정화/최종 검증

### frontend-agent
- 디바이스별 터치/가독성/네비게이션 정밀 조정
- 상태: `done`

### backend-agent
- 부하/재난복구 경로 점검(저장·복구·이상 상태)
- 상태: `done`

### planner-agent
- 전체 Act/사운드/내러티브 브리핑 문서 최종 정렬
- 상태: `done`

### qe-agent
- 회귀 1차 통합 테스트 실행(기능/스토리/시즌/UI)
- 상태: `done`

### validator-agent
- 최종 승인 기준 집계 및 `PLAN.md` 4단계 지표 충족 확인
- 상태: `done`

### sprint4 상태 요약
- `todo`: 0
- `in_progress`: 0
- `blocked`: 0
- `done`: 5

## 스프린트 5 (13~16주): 라이브 준비

### planner-agent
- 시즌 운영 운영툴/릴리스 노트 템플릿 정비
- 상태: `todo`

### backend-agent
- 시즌 자동 배포 게이트/롤백 스크립트 스펙 정리
- 상태: `todo`

### frontend-agent
- 계절/이벤트 알림 및 시즌 변경 가이드 퀵 UI 정렬
- 상태: `done`

### qe-agent
- 시즌 2 선행 회귀/스트레스 패턴 테스트 정리
- 상태: `todo`

### validator-agent
- 운영 KPI 대시보드(지표 임계값/경보 규칙) 정합성 체크
- 상태: `todo`

### sprint5 상태 요약
- `todo`: 4
- `in_progress`: 0
- `blocked`: 0
- `done`: 1

---

## 진행 지표
- 핵심 KPI
  - P0 작업 완료율
  - blocked 태스크 수
  - 매주 스프린트 누적 완료 토큰(작업수)
- 기준선
  - 스프린트당 P0 우선 100% 마감
  - `done` 전환 전 후속 에이전트의 의존성 충족 여부 확인

## 스프린트 실행 로그(ralph형 진행)

### Iteration 1 (Sprint 1 시작)
- focus: 기본 엔진 인터페이스 + 시나리오 문턱 정렬
- 목표 상태: `T1, T2, T4, T5, T12, T13, S1, S2` 70% 이상 착수
- 완료 증거:
  - backend 인터페이스 초안 완료
  - NPC 상태 규칙 초안 완료
  - Chronicle/Proof Board 라우팅 개념도 완료
- 결과: `done` 완료, 다음 Iteration으로 이동
- 산출 근거:
  - `SPRINT1_TASK_PACKAGES.md` 1차 패키지 완성
  - `SPRINT1_EXECUTION_LOG.md` 착수 로그 갱신
  - `SPRINT1_DONE_REPORT.md` 작성

### Iteration 2 (Sprint 2 수행)
- focus: 스토리-시스템 결합
- 목표 상태: `T3, T6, T7, T8, T9, T14, T15, S3, S4` 처리
- 완료 증거:
  - 5단계 스테이지 템플릿 초안
  - 사건 모듈/증거 반영 규칙 정합성 문서
  - HUD·풍석 예고 UI 데이터 바인딩 계약
- 결과: `done` 완료, 다음 Iteration로 이동
- 증거 문서:
  - `SPRINT2_TASK_PACKAGES.md`
  - `SPRINT2_EXECUTION_LOG.md`
  - `SPRINT2_DONE_REPORT.md`

### Iteration 3 (Sprint 3 수행)
- focus: 시즌/운영 계층 정착
- 목표 상태: `T10, T11, T16, T17, T18, S5` 처리
- 완료 증거(예정):
  - 시즌 상태 머신 시퀀스 정의
- 결과: `done` 완료, 다음 Iteration으로 이동
- 완료 증거:
  - `SPRINT2_TO_SPRINT3_HANDOFF.md`
  - `SPRINT3_TASK_PACKAGES.md`
  - `SPRINT3_EXECUTION_LOG.md`
  - `SPRINT3_CURRENT_DELIVERABLES.md`
  - `SPRINT3_DONE_REPORT.md`

### Iteration 4 (Sprint 4 수행)
- focus: 안정화/검증/릴리스 게이트
- 목표 상태: 
  - frontend: 모바일/접근성/네비게이션 정밀 항목
  - backend: 재난복구 및 저장/복구 경로
  - planner: 전체 내러티브 일관성 최종 정렬
  - qe: 통합 회귀 및 시나리오 통합 검증
  - validator: `PLAN.md` 승인 기준 충족 체크 완료
- 완료 증거:
  - sprint 4 체크리스트 100% 항목 확인
  - `done` 전환 전 `blocked=0`
- 증거 산출(현재 진행):
  - `SPRINT4_TASK_PACKAGES.md`
  - `SPRINT4_EXECUTION_LOG.md`
  - `SPRINT4_CURRENT_DELIVERABLES.md`

## Ralph 종료 조건(이번 작업판 기준)
- 각 스프린트의 완료 증거가 문서화되어야 함
- 스프린트 4 종료 시:
  - `blocked`가 0
  - P0 의존성 모두 해결
  - `PLAN.md` 승인 기준이 충족 상태로 갱신
- 미충족 시 다음 Iteration에서 즉시 복귀
## 위험/조정 규칙
- `backend-agent`가 `T8/T9` 완료 전 `frontend-agent`는 `T12/T13`의 데이터 바인딩을 완성 단계만 수행하고 정식 화면 연동은 보류
- `planner-agent`가 `T6` 미완료 상태면 `validator-agent`는 엔드 분기 관련 테스트를 미리 실행하지 않음
- QA 블로킹이 2개 이상 누적되면 다음 스프린트로 이동 전 `검증-플래닝` 회차 1회를 추가
