package bowling.domain.frame;

import bowling.domain.bowl.Bowls;
import org.junit.jupiter.api.Test;

import static bowling.domain.frame.FrameNumberTest.MIN_FRAME_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameTest {
    static final Frame INITIAL_FRAME = Frame.initialize();
    static final Frame LAST_FRAME = new Frame(new FrameNumber(FrameNumber.MAX), Bowls.initialize());

    @Test
    void Frame은_FrameNumber없이_생성_될_경우_예외를_발생_시킨다() {
        assertThatThrownBy(() -> {
            new Frame(null, Bowls.initialize());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Frame은_Bowls없이_생성_될_경우_예외를_발생_시킨다() {
        assertThatThrownBy(() -> {
            new Frame(MIN_FRAME_NUMBER, null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void next는_다음_Frame을_반환한다() {
        Frame nextFrame = INITIAL_FRAME.next();

        assertThat(nextFrame).isInstanceOf(Frame.class);
    }

    @Test
    void isLast는_마지막_프레임_여부를_반환한다() {
        assertAll(
                () -> assertFalse(INITIAL_FRAME.isLast()),
                () -> assertTrue(LAST_FRAME.isLast())
        );
    }
}
