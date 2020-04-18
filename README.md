# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 질문 삭제하기 리팩토링  

### 요구 사항  
> * 내가 쓴 글에 대해서 삭제할 수 있다.  
> * 내가 쓴 글에 댓글이 남겨져있지 않아야 삭제할 수 있다.  
> * 댓글이 있더라도, 내가 쓴 댓글만 있으면 삭제할 수 있다.  
> * 다른 사람이 쓴 댓글이 하나라도 있더라도 삭제할 수 없다.  

질문 삭제 수행 과정  
- questionId 통해 Question을 가져온다.  
- 해당 질문이 내가 쓴 글이 아닐 경우 Exception 처리를 한다.  
- 해당 질문의 댓글(Answer) 목록을 가져온다.  
- 해당 질문에 대해 순환하면서, 내가 쓴 댓글이 아니면 Exception 처리를 한다.  
- 질문과 답변을 삭제한다.
- 삭제한 내역을 DeleteHistory에 등록한다.  

### 클래스 및 책임 정리  

* Question - 질문 정보를 관리한다.    
    * 질문 ID, User를 가지고 있다.  
    * 질문을 삭제하는 기능을 갖는다. 
        * 해당 질문이 User에 대해 삭제 가능한지 여부를 판단해주어야 한다.  
        * 질문이 가진 댓글도 삭제해준다. (Answer의 삭제 기능을 호출하도록 한다.) 
        * 삭제 후에 DeleteHistory의 생성 기능을 이용해 deleteHistories를 만들어 반환해준다. (Answer, Question 모두)  
* Answer  - 댓글 정보를 관리한다.  
    * 댓글을 삭제하는 기능을 갖는다. 
    * 해당 댓글이 User에 대해 삭제 가능한지 여부를 판단해주어야 한다.
* DeleteHistory - 삭제 히스토리를 관리한다.  
    * 댓글이나 질문을 삭제했을 경우 DeleteHistory 객체를 생성해서 반환해주어야 한다.  

### 테스트 목록  

* QuestionTest 
    * 질문 작성한 사람이 다를 경우 삭제시 Exception을 뱉어줘야 한다.  
    * 삭제가 수행되면 Question 객체의 deleted 객체가 true로 변경되어야 한다.  
    * 삭제 히스토리 테스트 - 삭제를 수행하면 삭제 히스토리 정보가 하나씩 생성되는지 테스트?
* AnswerTest 
    * 답변 작성한 사람이 다를 경우 삭제시 Exception을 뱉어줘야 한다.   
    * 삭제가 수행되면 Answer 객체의 deleted 객체가 true로 변경되어야 한다.  
    * 삭제 히스토리 - 삭제를 수행하면 삭제 히스토리 정보가 하나씩 생성되는지 테스트?
* UserTest  
    * ? 


## 볼링 게임 점수판 요구사항 분석  

### 볼링 게임 규칙  

https://www.bowlinggenius.com/ 참고  

> * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태. X로 표시  
> * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태. 9|/으로 표시  
> * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태. 두 투구의 점수로 표시   
> * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시  
* 총 10 프레임으로 구성된다.  
* 한 프레임 당 2번의 투구를 진행한다.  
* 스트라이크의 경우에는 한번의 투구만 진행한다.  
* 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.  
* 현재 프레임에서 스트라이크가 나온 경우, 다음 2번째까지의 투구의 점수를 더한다.  
* 현재 프레임에서 스페어가 나온 경우, 다음 1번째의 투구의 점수를 더한다.  

### 기능 진행 흐름  

1. 플레이어 이름을 입력받는다.  
    * 플레이어 이름은 3글자의 영어 알파벳만 가능하다.  
2. 플레이어 이름을 포함한 점수판을 출력한다.  
    * 윗줄의 내용은 반복되므로 상수로 가지고 있도록 한다.    
3. 총 10프레임의 투구를 입력받아, 투구의 내용에 따른 점수판 표시를 더한 점수판을 반복하여 출력한다.  
    * 프레임 1개
        * 1번 투구가 10인 경우
            * 스트라이크. 2번은 진행하지 않는다.  
            * 다음 2회의 투구 후 합산 점수가 도출된다.    
            * X로 표시한다.        
        * 1번 투구가 10 미만 0 이상인 경우  
            * 1번 투구의 점수를 숫자로 표시한다.  
            * 2번 투구를 진행한다.  
                * 2번 투구와의 합이 10인 경우 - 스페어
                    * 다음 1회의 투구 후 합산 점수가 도출된다.      
                    * 2번 투구의 점수를 / 로 표시한다.   
                * 2번 투구와의 합이 10 미만인 경우 - 미스   
                    * 2번 투구의 점수를 숫자로 표시한다.  
                * 2번 투구가 0인 경우 - 거터 
                    * 2번 투구의 점수를 -로 표시한다.  
        * 1번 투구가 0인 경우  
            * 1번 투구의 점수를 -로 표시한다.  
            * 2번 투구를 진행한다.   
                * 2번 투구와의 합이 10인 경우 - 스페어
                    * 다음 1회의 투구 후 합산 점수가 도출된다.      
                    * 2번 투구의 점수를 / 로 표시한다.   
                * 2번 투구와의 합이 10 미만인 경우 - 미스   
                    * 2번 투구의 점수를 숫자로 표시한다.  
                * 2번 투구가 0인 경우 - 거터 
                    * 2번 투구의 점수를 -로 표시한다.             
        * 10프레임의 경우  
            * 1번 투구 스트라이크  
                * 3번 투구까지 진행할 수 있다.  
            * 2번 투구 스페어  
                * 3번 투구까지 진행할 수 있다.  
            * 2번 투구 미스 / 거터  
                * 게임이 종료된다.  
                
### 클래스 도출  

* BowlingGameController : 게임을 진행하는 Controller  
    * 입력받은 Player들에 의해 게임을 수행한다.  
    * View에게 필요한 정보를 보내고 받아온다.  
* Player: 각 Frame의 플레이를 수행하는 사람  
    * 이름 정보를 갖는다. 
        * 이름이 3글자가 아니면 Exception 처리를 수행한다.  
    * 진행한 Frame의 목록을 갖는다. 
    * play를 수행하며 Frame의 목록을 채워나간다.  
    * Frame 수를 이용해 게임 완료 여부를 판단할 수 있다.  
* Frame: 프레임 정보를 갖는 Interface (힌트 참고)  
    * 현재 Frame의 번호 정보를 갖는다.
        * Frame의 번호가 10이 넘거나 0보다 작으면 Exception 처리를 수행한다.  
    * play를 수행한다.  
    * 다음 Frame을 생성한다. (힌트 참고) <- State를 이용하자.   
    * 스트라이크/스페어/거터/미스 중 하나의 상태를 가질 수 있다. <- State  
        * 거터를 제외하  3개의 상태는 Frame이 끝나야만 가질 수 있다. 
        * 진행 전에는 Ready, 결과를 갖기 전에는 Playing 이라는 상태를 가지면 좋을까?
    * State를 통해 프레임 완료 여부를 판단할 수 있다.   
        * 스트라이크/스페어/미스는 Frame 완료
        * Gutter, Ready, Playing: Frame 미완료  
    * 프레임의 점수를 계산하여 가지고 있다. 
* State: 프레임이 갖는 상태 객체  
    * 각 상태에 맞는 play를 수행한다.  
    * 1번과 2번의 투구에서 쓰러뜨린 핀 정보를 갖는다.  
        * 각 투구 수 검증
            - 첫 투구: 0 이상 10 이하  
            - 두번째 투구: 첫번째 투구에 따라..
* NormalFrame: Frame 을 구현하는 Concrete 클래스 
    * 1-9 프레임을 가진다.  
* FinalFrame: Frame 을 구현하는 Concrete 클래스  
    * 10 프레임을 가진다.  
    * 3개의 처리는 어떻게 하면 좋을지 고민 필요 
* AdditionalFrame: FinalFrame에서 Spare나 Strike가 나올 경우 생성된다.  
    * 
* FrameResult: 각 Frame 의 결과를 가진다.  
* Board: 점수판 객체  
    * FrameResult를 통해 점수판을 만든다.     
* Score: 점수 객체  
    * 각 Frame에서 Score 객체를 이용해 현재 상태의 점수를 구한다.
    * Frame에서 현재 상태에서의 Score를 가지고 있는다.  
    * 상태별로 Strike와 Spare는 추가 점수 개수를 각각 2개, 1개를 가지고 있다고 가정한다. 
    * Frame에서 이 정보를 이용해 현재 점수를 계산할 수 있을지를 판단하고, 그렇지 않으면 다음 Frame으로 계산하는 것을 위임한다.  


### 테스트 코드 도출  

* Player 
    1. Player의 이름이 3글자가 아닌 경우 Exception 반환  
* Frame  
    1. 현재 Frame이 0에서 10사이에 있는 지 검증   
    2. 각 상태에 따른 Play 
    * 고민 필요  
    3. 다음 Frame 생성  
* State  
    1. 프레임의 완료 여부 
    2. 각 상태에서 play를 수행한 후의 상태 변경  
    3. 각 투구 수 검증  
* Score  
    1. 첫 투구가 Strike일 경우 이후 2개의 점수를 더한다.  
    2. 첫 두 투구가 Spare일 경우 이후 1개의 점수를 더한다. 
    3. Strike와 Spare외 각 상태 Play별 점수 산정 테스트