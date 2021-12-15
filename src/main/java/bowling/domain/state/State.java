package bowling.domain.state;

import bowling.domain.Frame;
import bowling.domain.Pins;

public interface State {
    /*
    Start
        Strike
          -> if final(first) then Progress
          -> else End
        Miss -> Progress
        Gutter -> Progress
    Progress
        Strike
          -> if final(second) then Progress
          -> else End
        Spare -> End
        Miss -> End
        Gutter -> End
    End

    ---
    # 투구 결과 상태
    스트라이크 Strike -> End | Progress(FinalFrame 인 경우)
    스페어 Spare -> End
    미스 Miss
     첫 번째 투구 -> Progress
     두 번째 투구 -> End
    거터 Gutter
     첫 번째 투구 -> Progress
     두 번째 투구 -> End

    # 게임 상태
    프레임 시작 Start
    프레임 진행 Progress
    프레임 종료 End
    */
    void pitch(Pins startPins, Pins fallDownPins, Frame frame);
}
