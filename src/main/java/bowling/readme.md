## 커밋 로그

```html
feat(general): add rough architecture
 - controller, view, domain 구조 추가
 - 점수, 이름 원시값 포장 객체 추가

feat(name): verify valid names
 - 테스트 코드 추가
 - 이름 시작 전/ 끝난 이후 공백 제거

feat(point): verify valid points
 - 테스트 코드 추가

feat(general): add overall architecture
 - frame, normalFrame, generalFrame 추가

feat(general): add business logic
 - 끝났는지 확인하는 인터페이스 추가
 - 볼링공을 던지는 인터페이스 추가
 - 인터페이스를 구현하는 프레임 추가
 - 게임을 진행하는 게임 클래스 추가

feat(gameFrames): add gameFrame class
 - 프레임을 표시해줄 수 있는 게임 프레임 생성

feat(view): add view method
 - 입력창과 출력창을 점수를 제외하고 추가 

fix(finalFrames): fix rule misunderstanding
 - 마지막 프레임에 보너스 투구를 1회로 수정
 - 출력 로직 추가

feat(resultView): add point print logic
```
