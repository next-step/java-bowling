## 1단계 - 질문 삭제하기 기능 리팩토링
**- 요구사항**
1. 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리

**- TODO List**

1. ~~Answer~~
   - ~~Answers 일급 컬랙션 생성~~
   - ~~검증로직 이동~~
   - ~~저장로직 이동~~
2. ~~Question~~
   - ~~검증로직 Question 메소드로 이동~~
   - ~~저장로직 이동~~
3. ~~DeleteHistory~~
   - ~~DeleteHistories 일급 컬랙션 생성~~
   - ~~삭제 히스토리에 Question 저장~~
   - ~~삭제 히스토리에 Answers 저장~~

**- 피드백**   

1. History가 아닌 question에 메시지를 보내 question을 삭제하고 그에 대한 history가 생기도록 할 수 있을 것 같아요!   
   - question, answer 객체가 삭제 메소드를 갖도록 변경하자