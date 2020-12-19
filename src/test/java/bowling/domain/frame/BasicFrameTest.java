package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.exception.NotHasTurnException;
import bowling.exception.ValidOverPointException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BasicFrameTest {

    @Test
    @DisplayName("스트라이크_하고_한번더_던지게_되면_익셉션이발생한다.")
    void 스트라이크_하고_한번더_던지게_되면_익셉션이발생한다() {
        Frame frame = BasicFrame.initFirst();

        frame.pitch(Point.valueOf(9));
        assertThatThrownBy(() -> frame.pitch(Point.valueOf(2)))
                .isInstanceOf(ValidOverPointException.class);
    }


    @Test
    @DisplayName("횟수를 초과하면 익셉션이 발생한다.")
    void throwCountException() {
        Frame frame = BasicFrame.initFirst();

        frame.pitch(Point.valueOf(10));
        assertThatThrownBy(() -> frame.pitch(Point.valueOf(2)))
                .isInstanceOf(NotHasTurnException.class);
    }


}