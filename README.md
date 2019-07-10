# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)


- 볼링을 친다 -> 1프레임을 치겠지 
           -> currentFrame 당 1~3번 칠수있다.
           -> currentFrame 1~2
           -> 마지막만 3번 가능
             
             
             
             
normalFrame
bowl(in: downPins, out: currentFrame)
    currentFrame.isFinish(out: boolean) // 현 프레임을 완료하는 지?
        currentFrame.next(out: nextFrame) createFrame
getState(in: index, out: state)

finalFrame
bowl(in: downPins, out: currentFrame)
    currentFrame.isFinish(out: boolean)
        currentFrame.next(out: X)  
        
        
Frame 
 -> bowl(in: pins, out: currentFrame)
 -> getState(out: frameState)
 -> isFinish(out: boolean)
 
normal state
 -> 1 round (gutter, hit, strike) : currentFrame 생성할 때 같이 준다면?
 ---
 -> 2 round (miss, spare)
1~2번

final state
 -> 1 round (gutter, hit, strike) 
 ---
 -> 2 round (miss, spare, strike) 
 
 -> 3 round (miss, spare, strike)
 
2~3번
1round : none

2round : FinalState(in: first, second) 
         // isFinish : T,F (first + second < 10 ) 
3round :

strike  / strike / strike
H       /strike  / strike
H       /spare   / strike
strike / hit     /  
-------------------
= first + second < 10 (한번더)
= second + third < 10 (stop)
first + second < 10 
first : 10 strike
else
spare









