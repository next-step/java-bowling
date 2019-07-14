package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Pins.*;
import static bowling.model.frame.state.Score.DEFAULT;
import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @DisplayName("게임을 진행하지 않은 경우 상태를 반환한다")
    @Test
    void getScore_default() {
        // when
        Frame frame = Frame.initialize();
        Score result = frame.getScore();

        // then
        assertThat(result).isEqualTo(DEFAULT);
    }

    @DisplayName("히트일 경우의 현 프레임 점수를 구한다")
    @Test
    void getScore_hit() {
        // given
        int countOfDownPins = 3;
        Pins first = Pins.valueOf(countOfDownPins);

        // when
        Frame frame = Frame.initialize();
        frame.bowl(first);
        Score result = frame.getScore();

        // then
        assertThat(result.getScore()).isEqualTo(3);
    }

    @DisplayName("거터인 경우 점수는 0점이다")
    @Test
    void getScore_gutter_zero() {
        // given
        int countOfDownPins = 0;
        Pins first = Pins.valueOf(countOfDownPins);

        // when
        Frame frame = Frame.initialize();
        frame.bowl(first);
        Score result = frame.getScore();

        // then
        assertThat(result.getScore()).isEqualTo(countOfDownPins);
    }

    @DisplayName("미스인 경우 점수를 구한다")
    @Test
    void getScore_miss() {
        // given
        Pins first = Pins.valueOf(MIN + 1);
        Pins second = Pins.valueOf(MIN + 1);

        // when
        Frame frame = Frame.initialize();
        frame.bowl(first);
        frame.bowl(second);
        Score result = frame.getScore();

        // then
        assertThat(result.getScore()).isEqualTo(2);
    }

    @DisplayName("스트라이크 인 경우 점수를 구한다")
    @Test
    void getScore_strike() {
        // given
        Pins first = DOWN_ALL;
        Pins second = Pins.valueOf(1);
        Pins third = Pins.valueOf(3);

        // when
        Frame frame = Frame.initialize();
        Frame nextFrame = frame.bowl(first);

        nextFrame.bowl(second);
        nextFrame.bowl(third);
        Score result = frame.getScore();

        // then
        assertThat(result.getScore()).isEqualTo(14);
    }

    @DisplayName("스트라이크 인 경우 점수를 구한다")
    @Test
    void getScore_strike_strike() {
        // when
        Frame frame = Frame.initialize();
        Frame nextFrame = frame.bowl(DOWN_ALL);
        Frame nextFrame2 = nextFrame.bowl(DOWN_ALL);
        nextFrame2.bowl(Pins.valueOf(3));
        nextFrame2.bowl(Pins.valueOf(4));
        Score result = frame.getScore();

        // then
        assertThat(result.getScore()).isEqualTo(23);
    }

    @DisplayName("스페어인 경우 점수를 구한다")
    @Test
    void getScore_spare() {
        // given
        Pins first = Pins.valueOf(MIN + 1);
        Pins second = Pins.valueOf(MAX - 1);
        Pins third = Pins.valueOf(3);

        // when
        Frame frame = Frame.initialize();
        frame.bowl(first);
        Frame nextFrame = frame.bowl(second);
        nextFrame.bowl(third);
        nextFrame.bowl(third);
        Score result = frame.getScore();

        // then
        assertThat(result.getScore()).isEqualTo(13);
    }
}