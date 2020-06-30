# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## Step1. Todo List
- [X] QnAService 삭제 리팩토링
- [X] 질문 삭제 시 데이터의 상태를 삭제 상태로 변경한다.
    - [X] 로그인 사용자와 질문한 사람이 다른 경우 예외 발생
    - [X] Answers 일급컬렉션에 삭제 메시지 전달
        - [X] 일급 컬렉션에 속한 모든 질문을 한번에 삭제할 수 있다.
        - [X] 답변이 한개라도 있는 경우 삭제 시도 시 예외 발생(기본)
            - [X] 답변이 있으나, 답변자가 모두 질문자인 경우 삭제 가능(예외)
    - [X] Answer 객체에서 삭제 처리할 수 있도록 리팩토링
- [X] QnAService 이력 저장 리팩토링
    - [X] 질문 삭제 시 이력이 남아야 한다.
        - [X] 질문 삭제 메서드 실행 시 삭제된 질문 이력을 반환한다.
        - [X] 답변 삭제 메서드 실행 시 삭제된 답변 이력을 반환한다.
        - [X] 답변 일급 컬렉션에서 삭제 메서드 실행 시 삭제된 답변 이력 컬렉션을 반환한다. 
        - [X] 질문 삭제 시 삭제된 모든 내용이 포함된 이력 컬렉션을 반환한다.
        - [X] 서비스 레이어에서 반환된 컬렉션을 저장한다.

## ~~Step2. Todo List~~ => `전부 폐기`
- 폐기 사유
    - 현재 구조는 객체를 선언한 이후 내부 상태를 변경하는 방식으로 설계 + 관심사 분리가 제대로 되어 있지 않음
    - 위 구조 상태에서는 볼링게임 진행을 마치 프레임이 자체적으로 진행하는 것처럼 되면서 설계가 어려움
    - 단순한 구조(생성자 적극 활용)로 변경 진행
    - + 마지막 프레임 요구사항을 제대로 파악 못했음
- Player
    - [X] 이름, Frame을 속성으로 갖는다.
    - [X] 첫 투구를 진행할 수 있다.
    - [X] 현재 진행중인 프레임의 숫자를 알 수 있다.
    - [ ] 투구해서 볼링 게임을 진행할 수 있다. (투구 후 현재 진행한 Frame의 상태를 확인할 수 있다.)
        - [X] 1 ~ 9 프레임에서 스트라이크로 종료 시 다음 Frame의 투구를 진행할 수 있다.
        - [X] 1 ~ 9 프레임에서 스트라이크가 아닌 경우 두번의 투구 후 다음 Frame을 진행 할 수 있다.
        - [ ] 투구 후 FrameStatus가 아니라 현재까지의 FrameResult 컬렉션을 내보내도록 리팩토링
        - [ ] 마지막 프레임(10 프레임)의 마지막 투구를 완료한 후에는 더이상 투구할 수 없다.
- Frame(interface)
    - 인터페이스로 추출
    - [X] 현재 프레임의 상태를 계산해서 FrameStatus 일급 컬렉션을 반환할 수 있음
    - [X] 현재 프레임이 마지막 프레임인지 알려줄 수 있음
    - [X] 현재 프레임의 마무리 여부를 알려줄 수 있음
- NormalFrame
    - [X] FrameResult, 다음 프레임(nextNormalFrame)을 속성으로 갖는다.
    - [X] 첫번째 투구로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.
    - [X] 두번째 투구를 실행할 수 있다.
        - [X] 현재 FrameResult가 완료됐다면 실행할 수 없다.
    - [X] 현재 프레임에서 다음 프레임을 생성할 수 있다.
        - [X] FrameResult가 온전하지 않은 경우 다음 프레임으로 넘어갈 수 없다.
    - [X] 해당 프레임이 마지막 프레임인지 알려줄 수 있다.
    - [X] 현재 Frame의 상태를 알려줄 수 있다.
- FinalFrame
    - 마지막 Frame은 진행과 결과 모두 특이하기 때문에 별도 관리
    - 실제로는 StrikeFrameResult와 NormalFrameResult가 조합된 특수 프레임
    - [X] FrameResult 두개 (FirstFrameResult, SecondFrameResult)를 속성으로 갖는다.
    - [X] 첫 투구가 Strike인 경우
        - [X] 두번째 투구도 Strike이면 해당 프레임 마무리 (총 2회 투구, StrikeFrameResult * 2)
        - [X] 두번째 투구가 Strike가 아니면 한번 더 진행 (총 3회 투구, StrikeFrameResult * 1, NormalFrameResult * 1)
    - [ ] 첫 투구가 Strike가 아닌 경우
        - [ ] 두번째 투구가 Spare 처리한 경우 한번 더 진행 (총 3회 투구)
            - [ ] 세번째 투구가 Strike인 경우 온전하게 마무리 (NormalFrameResult * 1, StrikeFrameResult)
            - [ ] 세번째 투구가 Strike가 아닌 경우 반푼이 NormalFrameResult로 마무리 (NormalFrameResult * 2, 하나는 반쪼가리)
        - [ ] 두번째 투구가 Spare 처리를 못한 경우(미스인 경우) 해당 프레임 마무리 (총 2회 투구, NormalFrameResult * 1)
    - [ ] 상황에 맞는 현재 프레임 상태를 알려줄 수 있다.(Final 요구사항 분석 및 구현 후 마지막에 구현)
- FrameResult(interface)
    - 프레임 결과에 대한 관심사를 다형성으로 처리하기 위해 인터페이스 구현
    - 프레임 결과는 스트라이크와 그 외로 구분할 수 있다.
    - [X] Strike 인지 판단 할 수 있다.
    - [X] 해당 Frame의 종료 여부를 알려줄 수 있다.
    - [X] 해당 프레임이 마지막 프레임인지 알려줄 수 있다.
    - [ ] 미스 여부를 알려줄 수 있다.
    - [X] 현재 프레임 상태를 알려줄 수 있다.
        - [X] FrameStatus 컬렉션을 반환하도록 리팩토링
    - [X] Strike
        - [X] strike 여부를 물어보면 true를 반환한다.
        - [X] 완료 여부를 물어보면 true를 반환한다.
        - [X] 언제나 현재 프레임 상태는 Strike이다.
        - [X] 상태 확인 시 Strike 상태를 가진 길이 1짜리 컬렉션 반환 (일급 컬렉션 활용)
    - [X] Normal
        - [X] 첫번째 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.
            - [X] 첫번째 맞춘 핀의 수는 0 ~ 9 사이의 값을 갖는다.
        - [X] 두번째 맞춘 핀의 수를 입력받을 수 있다.
            - [X] 첫번째 맞춘 핀의 수와 두번째 맞춘 핀의 수의 합이 10을 넘을 수 없다.
        - [X] 첫번째, 두번째 던져서 맞춘 핀의 수를 객체로 리팩토링
        - [X] 상황에 맞는 현재 프레임 상태를 알려줄 수 있다.
            - [X] 아직 진행중일 때(첫번째 투구만 진행됐을 때)의 상태를 알려줄 수 있다.
            - [X] 완료됐을 때(두번째 투구까지 진행됐을 때)의 상태를 알려줄 수 있다.
        - [X] 변경된 인터페이스에 맞게 리팩토링 진행
- FrameResultFactory
    - [X] 각 프레임의 첫번째 투구로 맞춘 핀의 수를 입력받아서 FrameResult를 생성할 수 있다.
        - [X] 입력값이 10이면 Strike를 반환한다.
        - [X] 입력값이 0 ~ 9 사이면 일반 결과를 반환한다.
        - [X] 입력값이 0 ~ 10 범위를 벗어날 수 없다.
    - [X] 마지막 프레임의 첫 투구로 맞춘 핀의 수를 입력 받아서 마지막 프레임 결과를 생성할 수 있다.
- NumberOfHitPin (<- ThrowResult)
    - [X] 프레임에서 한 회에 던져서 맞춘 갯수를 나타낸다.
    - [X] 0 ~ 10 사이의 값을 갖는다.
    - [X] 두 개의 ThrowResult를 더할 수 있다.
    - [X] 비교 연산을 위해 Comparable 구현
- FrameStatus
    - 스트라이크, 거터, 미스, 일반 숫자 등의 상태를 표현하는 enum
    - [X] 숫자 한 개로 상태를 찾아서 반환할 수 있다. (스트라이크, 거터, 일반숫자)
    - [X] 숫자 두 개로 상태를 찾아서 반환할 수 있다. (스페어, 거터, 일반숫자 - 미스)
    - [X] 위의 기능들을 NumberOfHitPin을 전달받아서 처리하도록 리팩토링
- FrameStatuses
    - FrameStatus 일급 컬렉션
    - [X] FrameStatus 리스트를 전달받아서 객체를 생성할 수 있다.
    - [X] 사이즈가 4 이상인 컬렉션은 생성할 수 없다.   
- ThrowResults
    - 요구사항 재분석 결과 스트라이크 외에는 던질때마다 결과를 출력해야 함. (현재 객체와 관심사가 다름)
    - [X] 프레임에서 던져서 맞춘 갯수들을 관리하는 일급 컬렉션이다.
    - [X] 최대 2회까지 존재한다. (현재는 일단 마지막 프레임은 고려하지 않는다.)
    - [X] 던져서 맞춘 핀의 갯수 총합은 0 ~ 10을 벗어날 수 없다.
    - [X] 스트라이크인 경우 값이 10인 ThrowResult 한개만 관리한다.
    
## Step2. Todo List(new)
- NumberOfHitPin
    - 1회 투구로 맞춘 핀의 수를 나타낸다.
    - [X] 0 ~ 10 사이의 값으로 생성할 수 있다.
    - [X] 같은 타입끼리 더할 수 있다.
    - [X] 같은 타입끼리 대소 비교를 할 수 있다.
- FrameStatus (interface)
    - 해당 프레임의 결과를 나타낸다.
    - 현재 프레임 내에서 다음 단계로 진행 할 수 있는지 여부에 관심을 갖는다.
    - NumberOfHitPin들을 갖고 있으며, 해당 내용을 기반으로 점수를 계산할 수 있다.
    - 현재 상태를 기반으로 해당 프레임의 다음 투구 상황을 진행할 수 있다.
- NormalFrameStatus
    - NumberOfHitPin을 최대 두개 가질 수 있는 FrameResult의 구현체
    - [X] 초구로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.
    - [X] 초구가 스트라이크인 경우 해당 프레임이 마무리된다.
    - [X] 초구가 스트라이크가 아닌 경우 1회 더 진행 후 프레임이 마무리된다.
    - [X] 현재 상태를 기반으로 결과를 FrameResults를 보여줄 수 있다.
- FinalFrameStatus
    - NumberOfHitPin을 최대 세개 가질 수 있는 FrameResult의 구현체
    - [X] 초구로 맞춘 핀의 수를 전달해서 객체를 생성할 수 있다.
    - [X] 초구가 스트라이크면 무조건 3회 투구 후 해당 프레임이 마무리된다.
    - [X] 초구가 스트라이크가 아닌 경우
        - [X] 스페어 처리 시 3회 투구 후 해당 프레임이 마무리된다.
        - [X] 스페어 처리 실패 시 2회 투구 후 해당 프레임이 마무리된다.
    - [X] 현재 상태를 기반으로 결과를 FrameResults를 보여줄 수 있다.
        - [X] 첫 투구만 진행됐을 때의 결과를 보여줄 수 있다.
        - [X] 두번재 투구까지 진행됐을 때의 결과를 보여줄 수 있다.
        - [X] 세번째 투구까지 완료됐을 때의 결과를 보여줄 수 있다.
- Frame (interface)
    - 현재 프레임의 결과와 번호를 나타낸다
    - 현재 프레임에서 다음 프레임으로 진행 할 수 있는지 여부에 관심을 갖는다.
    - 현재 FrameResult를 기반으로 점수를 계산할 수 있다.
- NormalFrame
    - 1 ~ 9 프레임을 나타내는 Frame 구현체
    - [X] 프레임 번호, NormalFrameStatus, 이전 프레임을 속성으로 갖는다.
    - [X] 현재 프레임이 마무리되지 않았을 때 현재 프레임을 진행할 수 있다.
        - [X] 마무리 된 프레임에 대해 다시 진행 시 예외 발생
    - [X] 현재 프레임이 마무리되면 다음 프레임으로 넘어갈 수 있다.
    - [X] 현재 프레임 결과를 보여줄 수 있다.
- FinalFrame
    - 10 프레임을 나타내는 Frame 구현체
    - 다음 프레임 따위 없다.
    - [X] 프레임번호, FinalFrameStatus, 이전 프레임을 속성으로 갖는다.
    - [X] 무조건 현재 프레임만 진행할 수 있다. (다음 프레임 없음)
    - [X] 현재 프레임 결과를 보여줄 수 있다.
- FrameResult
    - 프레임의 결과를 표현하는 enum
    - [X] 인자 한개만으로 해당하는 결과를 찾을 수 있다.(스트라이크, 거터, 일반 숫자)
    - [X] 인자 두개로 해당하는 결과를 찾을 수 있다.(스페어, 미스(일반 숫자))
- FrameResults
    - 프레임 결과들을 관리하는 일급 컬렉션
    - Frame에서 현재 상태를 확인할 때 반환된다.
    - [X] 최대 3개를 넘을 수 없다.
- Player
    - [X] 이름과 PlayerFrames을 속성으로 갖는다.
    - [X] 이름으로 객체를 생성할 수 있다.
    - [X] 초구를 던져서 볼링게임을 시작할 수 있다.
        - [X] 이미 초구를 굴린 참가자는 다시 초구를 굴릴 수 없다.
    - [X] 현재 상태를 계산할 수 있다.
        - [X] 초구를 굴리지 않은 상태에서는 현재 프레임의 완료 여부를 확인할 수 없다.
    - [X] 현재 프레임의 완료 여부를 확인할 수 있다.
        - [X] 초구를 굴리지 않은 상태에서는 현재 프레임의 완료 여부를 확인할 수 없다.
    - [X] 1 ~ 9 프레임을 진행할 수 있다.
        - [X] 현재 프레임이 진행 중이면 현재 프레임을 진행할 수 있다.
            - [X] 현재 프레임이 완료됐으면 예외가 발생한다.
        - [X] 현재 프레임이 완료됐으면 다음 프레임을 진행할 수 있다.
            - [X] 현재 프레임이 완료되지 않았으면 예외가 발생한다.
            - [X] 9 프레임까지 완료됐으면 더이상 진행할 수 없다.
    - [ ] 10 프레임을 진행할 수 있다.
        - [X] 9 프레임까지 완료된 상태에서만 진행할 수 있다.
        - [X] 마지막 프레임이 완료될 때까지 진행 할 수 있다.
        - [ ] 10 프레임에서 다음 프레임 생성 시도를 할 수 없다.
- PlayerFrames
    - [X] 플레이거 수행한 볼링 게임 기록을 갖고 있는 Frame 일급 컬렉션
    - [X] 초기에 빈 상태를 생성할 수 있다.
    - [X] 아무 것도 없는 경우 전달받은 프레임을 넣는다.
    - [X] 컬렉션이 비어 있지 않으면 마지막 요소에 따라 추가하거나 업데이트한다.
        - [X] 갖고 있던 프레임의 가장 마지막이 종료되지 않은 상태이면, 전달받은 Frame으로 업데이트 한다.
        - [X] 갖고 있던 프레임의 가장 마지막이 종료된 상태이면, 가장 끝에 해당 프레임을 추가한다.
    - [X] 최대 10개를 넘을 수 없다.
    - [X] 현재 상태를 계산할 수 있다.

### 참고사항: 마지막 프레임 결과 계산
- 초구 스트라이크인 경우
    - 무조건 3회 던진다.
- 초구가 스트라이크 아닌 경우
    - 2구째에 미스나면 2회로 끝난다.
    - 그 외 무조건 3회 던진다.
    
## Step2. 피드백 반영
- [X] FrameResult 계산에서 불필요한 if 제거
- [X] FinalFrame 생성을 NormalFrame의 next에서 진행 가능하도록 개선
    - [X] 실행 과정에서 FinalFrame을 별도로 관리하지 않고 일반 프레임처럼 다룰 수 있도록 개선 
- [X] Player와 기존 BowlingGame 객체의 책임 일부를 새로운 BowlingGame객체로 이관
    - [X] Player, BowlingGameResults(BowlingGameResult 일급컬렉션)을 속성으로 가짐
        - [X] 1차적으로 List<BowlingGameResult>로 구현
    - [X] 초구 진행 가능
        - [X] Player 리팩토링 필요(진행한 Frame 반환)
    - [X] 현재 프레임 진행 가능
        - [X] Player 리팩토링 필요(진행한 Frame 반환)
    - [X] 다음 프레임 진행 가능
        - [X] Player 리팩토링 필요(진행한 Frame 반환)
    - [X] 진행중인 프레임 완료 여부를 알려줄 수 있음
    - [X] BowlingGameResults는 기존 PlayerFrames와 매우 유사하나 게임이 진행된 결과만을 저장하도록 구현
    - [X] BowlingGameApplication에서 BowlingGame을 의존하도록 리팩토링
    - [X] 모든 기능 동작 확인되면 Player에서 PlayerFrames 일급 컬렉셔 제거 (currentFrame만 관리)
- [X] BowlingGameResult
    - [X] FrameResults를 속성으로 갖는다.

## Step3. 점수 계산 기능 구현
- [X] FrameScore
    - 각 프레임의 점수를 계산하는 객체
    - 투구가 끝날 때마다 새로 계산된다.
    - [X] 준비 상태, 점수값을 상태로 갖는다.
    - [X] 준비 상태를 알려줄 수 있다.
- [X] NormalBonusScore
    - FrameStatus에서 보너스 점수를 계산하면 생성된다.
    - [X] spareBonus, strikeBonus를 속성으로 갖는다.
    - [X] NumberOfHitPin 두개를 입력받아서 객체를 생성할 수 있다.
    - [X] 보너스 점수는 최대 10을 넘을 수 없다. (<- 10으로 재조정 필요)
    - [X] null을 받은 경우 0으로 계산한다.
- [X] FinalBonusScore
    - [X] 기본적으로 BonusScore와 동일
    - [X] Strike 보너스 판단 여부만 다르다 (생성시 둘다 null이 아니어야 한다.)
- [X] FrameResults
    - [X] 현재 상태 점수를 계산할 수 있다.
        - [X] 컬렉션에 있는 모든 점수를 합산해서 반환한다.
        - [X] 스페어가 존재하는 경우 스페어와 쌍이 되는 점수를 제외하고 남은 점수에 10을 더해서 반환한다.
- [X] Frame
    - [X] 현재 프레임 점수를 계산할 수 있다.
        - [X] 현재 프레임이 완료되지 않은 상태에서 점수를 계산할 수 있다.
        - [X] 현재 프레임이 완료된 상태에서 점수를 계산할 수 있다. (일반, 파이널)
            - [X] 현재 프레임 결과가 일반 상태일 때 점수를 계산할 수 있다.
            - [X] 현재 프레임 결과가 일반 상태가 아닐 때(스트라이크나 스페어인 경우) 점수를 계산할 수 있다.
    - [X] 이전 프레임 점수를 계산할 수 있다. (일반, 파이널)
        - [X] 이전 프레임이 스페어일 때 점수를 계산할 수 있다. - 일반
        - [X] 이전 프레임이 스트라이크일 때 점수를 계산할 수 있다. - 일반
        - [X] 이전 프레임이 일반 상태일 때 점수를 계산할 수 있다. - 일반
        - [X] 이전 프레임이 없을 때 점수를 계산할 수 있다. (첫 프레임에서만 발생)
        - [X] 이전 프레임이 스페어일 때 점수를 계산할 수 있다. - 파이널
        - [X] 이전 프레임이 스트라이크일 때 점수를 계산할 수 있다. - 파이널
        - [X] 이전 프레임이 일반 상태일 때 점수를 계산할 수 있다. - 파이널
    - [X] 더블 여부를 알려줄 수 있다. (터키 여부는 알려줄 필요 없다.) - 일반 프레임에서만
    - [X] 이전 프레임이 더블일 경우 더블이나 터키 계산을 진행할 수 있다. - 노멀 (2번째 전 프레임 점수 확정)
    - [X] 이전 프레임이 더블일 경우 더블이나 터키 계산을 진행할 수 있다. - 파이널
- [X] BowlingGameResult
    - [X] FrameScore도 속성으로 추가
- [X] BowlingGame
    - [X] FrameScore가 추가 된 BowlingGameResult 반영
        - [X] bowlFirst 메서드 적용
        - [X] bowlCurrentFrame 메서드 적용
        - [X] toNextFrame 메서드 적용
- [X] 볼링 점수 계산 로직 추가 (연속 스트라이크)
    - 스트라이크가 여러번 나오면 점수 계산 방식이 바뀜.............
    - 더블이건 터키건 결국 3프레임 안에 결판이 난다.
        - 현재 프레임 기준으로 이전 프레임이 더블인 경우
            - 현재 프레임이 스트라이크면 터키 처리
            - 현재 프레임이 스트라이크가 아니면 더블 처리
        - 늘 그렇듯 마지막 프레임은 이상하게 처리된다.
            - 9 프레임이 더블인 경우
                - 초구가 스트라이크면 8프레임 터키 처리
                    - 두번째 투구도 스트라이크면 9프레임 터키 처리
                    - 두번째 투구가 스트라이크가 아니면 9프레임 더블 처리
                - 초구가 스트라이크가 아니면 8프레임 더블 처리
            - 9 프레임이 그냥 스트라이크인 경우
                - 초구와 두번째 투구 모두 스트라이크면 9프레임 터키
                - 초구만 스트라이크면 9프레임 더블
    - [X] 더블 점수 계산 추가..
    - [X] 터키 점수 계산 추가..
- [X] OutputView
    - [X] 스코어 출력 기능 구현
- [X] BowlingGameApplication, BowlingGame
    - [X] 연속 스트라이크 계산이 가능한 9프레임으로 리팩토링
    - [X] 마지막 프레임 진행은 아예 분리해버릴 것

## Step4. 볼링 점수판(n명)
- [X] 기능 요구사항 구현
    - [X] BowlingGameApplication 에서 BowlingGame 컬렉션을 관리하도록 기능 개선
    - [X] 여러명을 입력받을 수 있도록 input 기능 개선
- [x] 9프레임 출력 디버깅 필요
    - [X] 가장 마지막 플레이어가 9프레임 마지막 투구 시 이상한 결과가 추가로 나옴
- [X] 한 플레이어가 해당 프레임을 모두 완료한 후에 다음 프레임으로 넘어가도록 구현
- [ ] 객체지향 생활체조 원칙 점검
    - [ ] BowlingGame의 BowlingGameResult 컬렉션을 일급 컬렉션으로 리팩토링
    - [ ] BowlingGameApplication에서 BowlingGame 컬렉션을 일급 컬렉션으로 리팩토링
        - [X] 기존 BowlingGame을 BowlingGamePlayer로 이름 변경
        - [ ] 현재 BowlingGameApplication에서 컬렉션 뭉치로 실행하는 로직을 BowlingGame 클래스로 이동
    - [ ] 인스턴스 변수 3개 이상인 클래스 있는지 확인하기
    - [ ] 디미터 법칙 어긴 부분 없는지 확인하기
    - [ ] 정말 최대한 게터를 다 걷어냈는지 확인하기
- [ ] 입력값 검증
    - [ ] 사용자 이름 입력 시 출력 문구 수정(몇번 플레이어인지 보이도록)
- [ ] 3단계 리뷰 반영
    - [ ] BowlinGame에서 유사한 기능을 유사한 메서드명으로 사용해서 혼란을 주는 부분 개선 방안 생각하기
    - [ ] BowlingGameApplication 개선안 생각해보기
    - [ ] BowlingGame에서 쓸모 없는 변수 선언이 있는지 확인해보기
