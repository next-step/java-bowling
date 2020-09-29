package bowling.domain;

import bowling.exception.GameOverException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {

    @DisplayName("FinalFrame 생성")
    @Test
    void create() {
        FinalFrame finalFrame = new FinalFrame();

        assertThat(finalFrame).isEqualTo(new FinalFrame());
    }

    @DisplayName("FinalFrame 투구 - 스트라이크/스페어처리 못한 경우")
    @Test
    void pitch() {
        FinalFrame frame = new FinalFrame();
        frame.pitch(1);
        frame.pitch(8);

        assertThat(frame.getPins()).containsExactly(new Pin(1), new Pin(8));
        assertThat(frame.getFallenPins()).containsExactly("1", "8");
    }

    @DisplayName("FinalFrame 투구 - 스트라이크/스페어시 3번 투구")
    @ParameterizedTest
    @CsvSource(value = {"1, 9, 1, 1, /, 1", "10, 10, 10, X, X, X", "10, 1, 2, X, 1, 2"})
    void pitch_third(int first, int second, int third, String expect, String expect2, String expect3) {
        FinalFrame frame = new FinalFrame();
        frame.pitch(first);
        frame.pitch(second);
        frame.pitch(third);

        assertThat(frame.getPins()).containsExactly(new Pin(first), new Pin(second), new Pin(third));
        assertThat(frame.getFallenPins()).containsExactly(expect, expect2, expect3);
    }

    @DisplayName("FinalFrame 투구 - 스트라이크/스페어 실패 후 3번째 투구시 exception 발생")
    @Test
    void pitch_noBonus() {
        FinalFrame frame = new FinalFrame();
        frame.pitch(1);
        frame.pitch(8);

        assertThatThrownBy(() -> frame.pitch(10))
                .isInstanceOf(GameOverException.class);
    }

    @DisplayName("FinalFrame 투구 - 최대 투구 회수 초과 exception 발생")
    @Test
    void pitch_overCount() {
        FinalFrame frame = new FinalFrame();
        frame.pitch(10);
        frame.pitch(10);
        frame.pitch(10);

        assertThatThrownBy(() -> frame.pitch(10))
                .isInstanceOf(GameOverException.class);
    }

    @DisplayName("FinalFrame 투구 - 10개 핀 이상 쓰러트린 경우 exception 발생")
    @Test
    void pitch_overPin() {
        FinalFrame frame = new FinalFrame();
        frame.pitch(9);

        assertThatThrownBy(() -> frame.pitch(10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"10, 10, 10, 30, 0", "10, 0, 2, 12, 0"})
    void getScore(int first, int second, int third, int expectScore, int expectRemain) {
        FinalFrame frame = new FinalFrame();
        frame.pitch(first);
        frame.pitch(second);
        frame.pitch(third);

        assertThat(frame.getScore()).isEqualTo(new Score(expectScore, expectRemain));
        assertThat(frame.hasScore()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"5, 5, 10, false", "1, 2, 3, true"})
    void getScore_notEnd(int first, int second, int expectScore, boolean expectHasScore) {
        FinalFrame frame = new FinalFrame();
        frame.pitch(first);
        frame.pitch(second);

        assertThat(frame.getScore()).isEqualTo(new Score(expectScore, 0));
        assertThat(frame.hasScore()).isEqualTo(expectHasScore);
    }
}
