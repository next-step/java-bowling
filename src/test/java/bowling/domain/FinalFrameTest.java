package bowling.domain;

import bowling.exception.GameOverException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {
    @DisplayName("FinalFrame 투구 - 스트라이크/스페어처리 못한 경우")
    @Test
    void pitch() {
        FinalFrame frame = new FinalFrame();
        frame.pitch(1);
        frame.pitch(8);

        assertThat(frame.getFallenPins()).isEqualTo("1|8");
    }

    @DisplayName("FinalFrame 투구 - 스트라이크/스페어시 3번 투구")
    @ParameterizedTest
    @CsvSource(value = {"1, 9, 1, 1|/|1", "10, 10, 10, X|X|X", "10, 1, 2, X|1|2"})
    void pitch_third(int first, int second, int third, String expect) {
        FinalFrame frame = new FinalFrame();
        frame.pitch(first);
        frame.pitch(second);
        frame.pitch(third);

        assertThat(frame.getFallenPins()).isEqualTo(expect);
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

    @DisplayName("점수 계산 - 3번 투구")
    @ParameterizedTest
    @CsvSource(value = {"10, 10, 10, 30", "10, 0, 2, 12"})
    void getScore(int first, int second, int third, int expectScore) {
        FinalFrame frame = new FinalFrame();
        frame.pitch(first);
        frame.pitch(second);
        frame.pitch(third);

        assertThat(frame.getScore()).isEqualTo(expectScore);
        assertThat(frame.hasScore()).isTrue();
    }

    @DisplayName("점수 계산 - 2번 투구")
    @ParameterizedTest
    @CsvSource(value = {"5, 5, 10, false", "1, 2, 3, true"})
    void getScore_notEnd(int first, int second, int expectScore, boolean expectHasScore) {
        FinalFrame frame = new FinalFrame();
        frame.pitch(first);
        frame.pitch(second);

        assertThat(frame.getScore()).isEqualTo(expectScore);
        assertThat(frame.hasScore()).isEqualTo(expectHasScore);
    }
}
