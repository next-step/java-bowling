package bowling.domain;

import bowling.exception.GameOverException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        assertThat(frame.getPins()).containsExactly(new Pin(10));
        assertThat(frame.getScore()).containsExactly("X");
    }

    @DisplayName("NormalFrame 투구 - spare")
    @Test
    void pitch2() {
        NormalFrame frame = NormalFrame.firstFrame();
        frame.pitch(5);
        frame.pitch(5);

        assertThat(frame.getPins()).containsExactly(new Pin(5), new Pin(5));
        assertThat(frame.getScore()).containsExactly("5", "/");
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

    @Test
    void getScore() {
        NormalFrame frame = NormalFrame.firstFrame();
        frame.pitch(2);
        frame.pitch(1);

        assertThat(frame.getTotalScore()).isEqualTo(new Score(3, 0));

    }
}
