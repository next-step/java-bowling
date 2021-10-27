package bowling.domain.userframeresult;

import static org.junit.jupiter.api.Assertions.assertTrue;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.score.Pin;
import bowling.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserFrameResultTest {

    @Test
    @DisplayName("현재 user의 볼링게임이 종료되었는지 확인할 수 있다.")
    void isFinishedTest() {

        // given
        Frame frame = FinalFrame.createFinalFrame();
        frame.bowling(Pin.of(1));
        frame.bowling(Pin.of(1));
        User user = User.of("pjs");
        UserFrameResult userFrameResult = UserFrameResult.fromUserAndFirstFrame(user, frame);

        // when
        boolean result = userFrameResult.isFinished();

        // then
        assertTrue(result);
    }

}