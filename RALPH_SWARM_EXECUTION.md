# RALPH_SWARM_EXECUTION.md

## 목적
`SPRINT 4`까지 작업이 실제로 마무리될 때까지, 완료를 증명 가능한 산출물과 다음 단계 전환 규칙을 유지한다.

## 반복 규칙(Iteration)
1. 작업 착수 전: 이전 반복의 `blocked` 목록 비우고 우선순위 정렬
2. 병렬 수행: 역할별 독립 과업 동시 진행
3. 중간 점검: 스프린트별 완료증거 3개 이상 확보
4. 승인 게이트: P0 미해결항목이 있으면 다음 스프린트 전환 금지
5. 종료 판단: `S4` 검증 항목 + 스프린트4 승인 체크리스트 충족

## Iteration 1~4 타임라인

### Iteration 1 (Sprint 1)
- in-scope: `T1, T2, T4, T5, T12, T13, S1, S2`
- 완료 산출(진행 중 산출물 생성 완료):
  - 네피아 런 파이프라인 기본 인터페이스 초안
  - NPC 관계 상태 규칙 v1
  - Chronicle/ProofBoard UX 요구사항
  - 스프린트 1 수용성 테스트 케이스 2개
- 상태: `done`
- 증거: `SPRINT1_TASK_PACKAGES.md`, `SPRINT1_EXECUTION_LOG.md`, `SPRINT1_DONE_REPORT.md`
- 실패 대응:
  - blocked 1건 이상이면 즉시 의존성 리다자인
  - 각 블로커에 대한 owner 재할당

### Iteration 2 (Sprint 2)
- in-scope: `T3, T6, T7, T8, T9, T14, T15, S3, S4`
- 완료 산출:
  - 스테이지 템플릿 v1
  - 사건 모듈 스키마 v1 + 증거 가중치 규칙
  - 라네이레 정보 업데이트 조건 표
  - 시즌 예고/연대기 UI 바인딩 인터페이스
- 상태: `done`
- 근거 문서:
  - `SPRINT2_TASK_PACKAGES.md`
  - `SPRINT2_CURRENT_DELIVERABLES.md`
  - `SPRINT2_EXECUTION_LOG.md`
  - `SPRINT2_DONE_REPORT.md`
- 실패 대응:
  - 설계 충돌 시 최소한 1회 통합 회의 실행
  - FE/BE의 타입 계약 미스매치 시 인터페이스 단일화

### Iteration 3 (Sprint 3)
- in-scope: `T10, T11, T16, T17, T18, S5`
- 완료 산출:
  - 선택 기록 가중치 규칙 v1
  - 시즌/펙트 보정 룰 초안
  - 시즌 상태 머신 시퀀스 정의
  - 시즌 변형 세트 로더/보스 연동 규칙
  - S5 엔드 도달성 체크리스트 초안
- 상태: `done`
- 근거 문서:
  - `SPRINT2_TO_SPRINT3_HANDOFF.md`
  - `SPRINT3_TASK_PACKAGES.md`
  - `SPRINT3_CURRENT_DELIVERABLES.md`
  - `SPRINT3_EXECUTION_LOG.md`
- 완료 근거 문서:
  - `SPRINT3_DONE_REPORT.md`
- 실패 대응:
  - 시즌 상태 전이 실패 시 시즌 시나리오/데이터 분리 리팩터
  - 네피아 변형 이벤트 우선순위 재조정

### Iteration 4 (Sprint 4)
- in-scope: 안정화 + 최종 승인
- 완료 산출:
  - UI 안정성/접근성 점검 보고
  - backend 저장복구 경로 점검 보고
  - 내러티브 연속성 최종 정렬 보고
  - 회귀 테스트 통합 요약
  - 승인 기준 충족 시트
- 상태: `done`
- 실패 대응:
  - blocker가 남으면 스프린트 재실행 1회 허용
  - 반복 재발 시 구조적 리스크로 상위 의사결정 요청
  - 증거 산출:
    - `SPRINT4_DONE_REPORT.md`
    - `SPRINT4_TO_SPRINT5_HANDOFF.md`

### Iteration 5 (Sprint 5)
- in-scope: 라이브 준비(운영 툴, 배포 게이트, 시즌2 준비)
- 완료 산출(예정):
  - 배포 게이트·롤백 규칙 정합성
  - 시즌2 운영 템플릿/이벤트 메시지 통합
  - 라이브 회귀 시나리오 초안
- 상태: `in_progress`
- 시작 근거: `SPRINT4_TO_SPRINT5_HANDOFF.md`
- 실패 대응:
  - 운영 리스크 항목은 우선적으로 백로그 상단 고정
  - 72h 안정성 조건 미충족 시 일정 조정 요청

## 스프린트 4 종료 체크리스트
- [x] `blocked = 0` 유지
- [x] `SPRINT 1~3` 주요 산출물 검증 완료
- [x] S1~S5 전부 실행 및 결과 반영
- [x] P0 항목 미해결 0건
- [x] PLAN 승인 기준 충족 상태 업데이트
- [x] 다음 단계(라이브 이벤트 확장) 전 이행 문서 준비

## 다음 단계 전환 명령(요약)
- Sprint4 완료 후 `PLAN.md`의 5단계(라이브 준비)로 바로 이행
- 각 스쿼드별 결과는 `SWARM_ASSIGNMENTS.md`의 해당 상태 칸으로 반영
