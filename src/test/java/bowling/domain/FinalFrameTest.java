package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

public class FinalFrameTest {

    private static final int BOWLING_MAX_NUMBER = 10;

    @Test
    @DisplayName("쓰러뜨린 볼링핀 수 확인")
    public void 쓰러뜨린_볼링핀수() {
        Frame finalFrame = FinalFrame.init();

        finalFrame.add(Pitch.of(10));
        finalFrame.add(Pitch.of(0));

        assertEquals(finalFrame.getFirstOfKnockDown(), 10);
        assertEquals(finalFrame.getSecondOfKnockDown(), 0);
    }

    @Test
    @DisplayName("10번째 보너스볼 정상 입력되는지")
    public void 첫번째_프레임_쓰러뜨린_볼링핀수() {
        Frame frame = FinalFrame.init();
        FinalFrame finalFrame = (FinalFrame) frame;

        frame.add(Pitch.of(BOWLING_MAX_NUMBER));
        frame.add(Pitch.of(BOWLING_MAX_NUMBER));
        frame.add(Pitch.of(BOWLING_MAX_NUMBER));
        assertEquals(finalFrame.getBonusOfKnockDown(), BOWLING_MAX_NUMBER);
    }

}
