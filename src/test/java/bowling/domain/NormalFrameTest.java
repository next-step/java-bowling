package bowling.domain;

import bowling.exception.GameOverException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTest {

    @DisplayName("NormalFrame 생성")
    @Test
    void create() {
        NormalFrame normalFrame = new NormalFrame(0);

        assertThat(normalFrame).isEqualTo(new NormalFrame(0));
    }

    @DisplayName("NormalFrame 생성 - 유효하지 않은 index exception 발생")
    @Test
    void create_invalid() {
        assertThatThrownBy(() -> new NormalFrame(9))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("NormalFrame 생성 - firstFrame")
    @Test
    void firstFrame() {
        NormalFrame normalFrame = NormalFrame.firstFrame();

        assertThat(normalFrame).isEqualTo(new NormalFrame(0));
    }

    @DisplayName("NormalFrame 생성 - 다음 프레임")
    @Test
    void next() {
        NormalFrame frame = NormalFrame.firstFrame();
        NormalFrame nextFrame = frame.next();

        assertThat(nextFrame).isEqualTo(new NormalFrame(1));
    }

    @DisplayName("NormalFrame 투구 - strike")
    @Test
    void pitch() {
        NormalFrame frame = NormalFrame.firstFrame();
        frame.pitch(10);

        assertThat(frame.getFallenPins()).isEqualTo("X");
    }

    @DisplayName("NormalFrame 투구 - spare")
    @Test
    void pitch2() {
        NormalFrame frame = NormalFrame.firstFrame();
        frame.pitch(5);
        frame.pitch(5);

        assertThat(frame.getFallenPins()).isEqualTo("5|/");
    }

    @DisplayName("NormalFrame 투구 - 최대 투구 회수 초과 exception 발생")
    @Test
    void pitch_overCount() {
        NormalFrame frame = NormalFrame.firstFrame();
        frame.pitch(1);
        frame.pitch(2);

        assertThatThrownBy(() -> frame.pitch(3))
                .isInstanceOf(GameOverException.class);
    }

    @DisplayName("NormalFrame 투구 - 10개 핀 이상 쓰러트린 경우 exception 발생")
    @Test
    void pitch_overPin() {
        NormalFrame frame = NormalFrame.firstFrame();
        frame.pitch(10);

        assertThatThrownBy(() -> frame.pitch(2))
                .isInstanceOf(GameOverException.class);
    }

    @DisplayName("점수 계산")
    @ParameterizedTest
    @CsvSource(value = {"5, 5, 10, false", "2, 1, 3, true"})
    void getScore(int first, int second, int expectScore, boolean expectHasScore) {
        NormalFrame frame = NormalFrame.firstFrame();
        frame.pitch(first);
        frame.pitch(second);

        assertThat(frame.getScore()).isEqualTo(expectScore);
        assertThat(frame.hasScore()).isEqualTo(expectHasScore);
    }

    @DisplayName("점수 계산 스트라이크 일때")
    @Test
    void getScore_Strike() {
        NormalFrame frame = NormalFrame.firstFrame();
        frame.pitch(10);

        assertThat(frame.getScore()).isEqualTo(10);
        assertThat(frame.hasScore()).isEqualTo(false);
    }
}
