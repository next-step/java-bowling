package bowling.model.frame;

import bowling.model.DownPin;
import bowling.model.frame.state.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.DownPin.*;
import static bowling.model.frame.state.Score.DEFAULT;
import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @DisplayName("볼링을 한번도 안쳤을 시 기본 상태")
    @Test
    void getResult_none_default() {
        // when
        Frame frame = Frame.initialize();

        // then
        assertThat(frame.getScore()).isEqualTo(DEFAULT);
    }

    @DisplayName("미스일 시 점수를 반환한다")
    @Test
    void getResult_bowlOne_returnMiss() {
        // given
        Frame frame = Frame.initialize();
        frame.bowl(DownPin.valueOf(3))
                .bowl(DownPin.valueOf(5));

        // when
        FrameResult frameResult = frame.getResult();

        // then
        assertThat(frameResult.getScore()).isEqualTo(8);
    }

    @DisplayName("스트라이크 세번일 경우 점수를 구한다")
    @Test
    void bowl_strikeX3_resultFirstFrame() {
        // when
        Frame frame = Frame.initialize();
        frame.bowl(DOWN_ALL)
                .bowl(DOWN_ALL)
                .bowl(DownPin.DOWN_ALL);
        FrameResult frameResult = frame.getResult();

        // then
        assertThat(frameResult.getScore()).isEqualTo(30);
    }

    @DisplayName("히트일 경우의 점수를 구하는데 성공한다")
    @Test
    void getScore_hit() {
        // given
        int countOfDownPins = 3;
        DownPin first = DownPin.valueOf(countOfDownPins);

        // when
        Frame frame = Frame.initialize();
        frame.bowl(first);
        Score result = frame.getScore();

        // then
        assertThat(result.getScore()).isEqualTo(countOfDownPins);
    }

    @DisplayName("거터인 경우 0점이다")
    @Test
    void getScore_gutter_zero() {
        // given
        int countOfDownPins = 0;
        DownPin first = DownPin.valueOf(countOfDownPins);

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
        DownPin first = DownPin.valueOf(MIN + 1);
        DownPin second = DownPin.valueOf(MIN + 1);

        // when
        Frame frame = Frame.initialize();
        frame.bowl(first)
                .bowl(second);
        Score result = frame.getScore();

        // then
        assertThat(result.getScore()).isEqualTo(2);
    }

    @DisplayName("스트라이크 인 경우 점수를 구한다")
    @Test
    void getScore_strike_strike() {
        // when
        Frame frame = Frame.initialize();
        Frame nextFrame = frame.bowl(DOWN_ALL);
        nextFrame.bowl(DOWN_ALL)
                .bowl(DownPin.valueOf(3));
        Score result = frame.getScore();

        // then
        assertThat(result.getScore()).isEqualTo(23);
    }

    @DisplayName("스트라이크 인 경우 점수를 구한다")
    @Test
    void getScore_strike_strike_miss() {
        // when
        Frame frame = Frame.initialize();
        frame.bowl(DOWN_ALL)
                .bowl(DOWN_ALL)
                .bowl(DownPin.valueOf(3))
                .bowl(DownPin.valueOf(5));
        Score result = frame.getScore();

        // then
        assertThat(result.getScore()).isEqualTo(23);
    }

    @DisplayName("스페어인 경우 점수를 구한다")
    @Test
    void getScore_spare() {
        // given
        DownPin first = DownPin.valueOf(MIN + 1);
        DownPin second = DownPin.valueOf(MAX - 1);
        DownPin third = DownPin.valueOf(3);

        // when
        Frame frame = Frame.initialize();
        frame.bowl(first)
                .bowl(second)
                .bowl(third);
        Score result = frame.getScore();

        // then
        assertThat(result.getScore()).isEqualTo(13);
    }
}