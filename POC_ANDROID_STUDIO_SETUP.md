# 에테르의 눈 PoC - Android Studio 빌드/설치 가이드

## 1) 프로젝트 열기
1. Android Studio에서 `File > Open`
2. 이 폴더(`/Users/jihun/StudioProjects/Ether`)를 선택
3. Gradle Sync 완료 대기

## 2) 모듈/SDK 확인
- Gradle sync 중 `Gradle plugin` 동기화 시점에 Android SDK 34가 필요합니다.
- Android Studio에서 설치가 안 돼 있다면:
  - `Tools > SDK Manager`에서
  - Android SDK 34, Android SDK Build-Tools 34.x, Android Emulator를 설치
- 터미널로 직접 빌드할 경우, 프로젝트 루트에 `local.properties`를 추가:
  - `sdk.dir=/실제/SDK/경로`
  - 예: `sdk.dir=/Users/사용자/Library/Android/sdk`
- Android Studio에서 열린 뒤에는 `local.properties` 없이도 SDK 경로를 감지하는 경우가 많습니다.

## 3) 실행 대상 설정
1. 우측 상단 디바이스 목록에서 연결된 휴대폰 또는 에뮬레이터 선택
2. 런/디버그 구성에서 `app` 모듈을 선택 (자동 생성됨)

## 4) 빌드·실행
- `Run > Run 'app'`
- 빌드 후 앱이 기기에 설치되면 화면에
  - `에테르의 눈 PoC`
  - `런 1턴 진행`, `긴급 정화`, `리셋` 버튼이 표시됨

## 5) 사용 방법 (PoC)
- **런 1턴 진행**: 랜덤 이벤트 1회 실행, 오염/항체/증거 값이 변경되고 연대기 로그가 추가됨
- **긴급 정화**: 즉시 오염을 일부 감소시킴
- **리셋**: 상태 초기화
- 최근 이벤트 카드에서 발생한 텍스트를 확인해 내부 스토리/로그 흐름을 체감 가능

## 6) 만약 빌드가 안 될 때
- `Build > Clean Project` → `Build > Rebuild Project`
- `File > Invalidate Caches / Restart`
- `settings.gradle.kts`, `build.gradle.kts`에서 plugin / SDK 버전 변경이 있었는지 확인
