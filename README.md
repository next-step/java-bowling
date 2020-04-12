# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)


##설계
* 플레이어입력 -> 이름3글자까지 -> 1프레임투구 -> 스트라이크이면 -> 2프레임투구 
                                     -> 스트라이크아니면 -> 1프레임투구 -> 2프레임투구
                            .........
                            10프레임투구 -> 2번째투구 -> 1번째투구,2번째투구 결과에 따라 -> 3번째투구
* 1~9프레임 룰 : 1 ~ 9 프레임 시도횟수는 최대 2번
               1회 쓰러트린핀 최대 10
               1 ~ 9 프레임 쓰러트린 핀 합은 최대 10
* 10프레임룰 : 시도횟수는 최대 3번
             1회 쓰러트린핀 최대 10
             첫번째 스트라이크 두번째 스트라이크 세번째 스트라이크
             첫번째 스트라이크 두번째 스트라이크X -> 두~세번째 합 10
             첫번째 스트라이크X -> 첫~두번째 합 10 -> 세번째 
             첫번째 스트라이크X -> 첫~두번째 < 10 끝
             
* 스트라이크 : X : 1회투구에 10개 
  스페어 : n|/  : 1~2회투구에 10개   -> 전투구 + 현재투구 로 해야 10프레임때 가능할
  미스 : n1/n2  : 1~2회투구 < 10개
  거터 : -      : 각 투구 0일때듯
  
##프로그램목록
* InputView - 플레이어 / 프레임
* Player - 플레이어정보 (name) / 이름벨리데이션 / Game정보생성(Frame, NormalFrame, FinalFrame, Scores, Score, 현재프레임)
* Frame - 프레임정보  / 총10프레임 / 투구횟수체크 / 스코어체크
* NormalFrame - 1~9프레임 / 각 프레임당 투구 max 2 / 스코어 합 max 10 / 
* FinalFrame - 10프레임  / 각 프레임당 투구 max 2 ~ 3 / 스코어 합 max 30 / 
* Scores - 점수정보 (List<Integer> ) / 스코어 체크
* OutputView - 점수판 / 첫번째 줄 (이름, 프레임수) / 
                        두번째줄 (이름, 점수[X , N|/, N|N, N|- )
                        
                        
                        
사용자이름을입력받는다.
플레이어생성한다 
게임을생성한다. - 프레임정보 - 스코어즈정보 - 스코어정보 (5,5)
1구던질때 score생성, scores생성, normalFrame생성
2구던질때 score생성, scores.add 
=======다음프레임 ========
1구던질때 score생성, scores생성, normalFrame.add
2구던질때 score생성, scores.add 



  
                               
