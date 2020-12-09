## 질문 삭제하기 요구사항
- 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제상태(deleted - boolean type)로 변경한다.
- 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- 답변이 없는 경우 삭제가 가능하다.
- 질문자와 답변글의 모든 답변자 같은 경우 삭제가 가능하다.
- 질문을 삭제할 때 답변 또한 삭제해야 하며 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
- 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
- 질문과 답변 삭제이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

## 프로그래밍 요구사항
- qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다.
이 메소드는 단위테스트 하기 어려운 코드와 가능한 코드가 섞여있다.
- 단위 테스트 하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트가능한 코드에 대해 단위테스트를 구현한다.

### 힌트
- 객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
- 일급 콜렉션을 쓴다.
    - Question의 List를 일급 콜렉션으로 구현해 본다.
- 3개이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
    - 인스턴스 변수의 수를 줄이기 위해 도전한다.



#### 도메인 클래스 분석

- AbstractEntity(공통 도메인 관리)
    - id (인덱스)
    - createdAt (생성날짜)
    - updateAt (수정날짜)    
- Answer(답변)
    - User user(유저 객체정보를 담음)
    - Question question(질문 객체 정보를 담음)
    - String contents (내용)
    - boolean deleted (삭제 여부)
- ContentType (질문인지 답변인지 이넘)
    - QUESTION 질문
    - ANSWER 답변
- DeleteHistory (삭제기록)
    - Long id (삭제기록 인덱스) 
    - ContentType ContentType (질문인지 답변인지 확인하는 클래스)
    - Long contentId (삭제한 콘텐츠 인덱스)
    - User deletedBy (누구에게 삭제되었는지 유저객체)
    - LocalDateTime createdDate (삭제된 날짜) 
- Question(질문)
    - String title (제목)
    - String contents (내용)
    - User writer (글쓴이 유저객체)
    - List<Answer> answers (질문에 대한 답변 리스트 )
    - boolean deleted (삭제여부)
- User
    - String userId(유저의 아이디)
    - String password(유저의 비밀번호)
    - String name(유저의 이름)
    - String email (유저의 이메일)
    
    
- QnAService
    - deleteQuestion
        - 질문을 인덱스로 가져옵니다.
        - 질문의 주인이 로그인 유저인지 확인.
            - 맞지 않을경우 질문삭제 권한이 없다고 고지
        - 질문에 대한 답변들을 찾아옵니다.
        - 질문에 대한 답변들의 리스트에서 나 이외에 쓴 글이 있을경우 삭제를 할 수 없습니다.
        - DeleteHistory를 만듭니다.
        - 질문의 상태를 삭제 상태로 만듭니다.
        - 삭제 기록에 질문을 지금시간으로 넣습니다.
        - 질문들의 삭제상태로 만듭니다.
        - 삭제 기록을 남깁니다.
        - 모두 저장합니다.
        
- 만들어야 하는 테스트
    - Answer
    - Answers  
    - Question
    - DeleteHistory
    - DeleteHistories
    - User
    
    