## [NEXTSTEP 플레이그라운드의 미션 진행 과정](https://github.com/next-step/nextstep-docs/blob/master/playground/README.md)

---
## 학습 효과를 높이기 위해 추천하는 미션 진행 방법

---
1. 피드백 강의 전까지 미션 진행 
> 피드백 강의 전까지 혼자 힘으로 미션 진행. 미션을 진행하면서 하나의 작업이 끝날 때 마다 add, commit
> 예를 들어 다음 숫자 야구 게임의 경우 0, 1, 2단계까지 구현을 완료한 후 push

![mission baseball](https://raw.githubusercontent.com/next-step/nextstep-docs/master/playground/images/mission_baseball.png)

---
2. 피드백 앞 단계까지 미션 구현을 완료한 후 피드백 강의를 학습한다.

---
3. Git 브랜치를 master 또는 main으로 변경한 후 피드백을 반영하기 위한 새로운 브랜치를 생성한 후 처음부터 다시 미션 구현을 도전한다.

```
git branch -a // 모든 로컬 브랜치 확인
git checkout master // 기본 브랜치가 master인 경우
git checkout main // 기본 브랜치가 main인 경우

git checkout -b 브랜치이름
ex) git checkout -b apply-feedback
```

## 숫자 야구 게임 구현 기능 목록
- UI
  - 입력
    - 숫자를 입력해 주세요 : xxx(입력 받음)
  - 출력
    - 규칙에 맞게 볼, 스트라이크, 낫싱을 출력받는다
    - 정답을 맞추면 문구 출력

- 비즈니스 로직
  - 새로운 게임을 시작
  - 랜덤으로 게임에 사용될 3자리 숫자를 제시
  - 제시한 3자리 숫자를 계산하여 결과를 도출

- 클래스 구조
  - 게임 준비
  - 게임 결과 비교(심판)
  - 게임 진행
  - 입력 화면(InputView)
  - 결과 화면(ResultView)