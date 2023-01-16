package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MissTest {

    @Test
    void 종료_여부_판단() {
        assertThat(new Miss(new Pin(0), new Pin(0)).isFinished()).isTrue();
    }

    @Test
    void 게임진행_예외발생() {
        assertThatThrownBy(() -> new Miss(new Pin(0), new Pin(0)).bowl(new Pin(0)))
                .isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"0, 0, -, -", "5, 4, 5, 4", "5, 0, 5, -"})
    void 메시지_출력(int firstPinAmount, int secondPinAmount, String expectedFirst, String expectedSecond) {
        assertThat(new Miss(new Pin(firstPinAmount), new Pin(secondPinAmount)).toString())
                .isEqualTo(expectedFirst + Miss.MISS_MESSAGE + expectedSecond);
    }

    @Test
    void Score_생성() {
        assertThat(new Miss(new Pin(1), new Pin(2)).score())
                .isEqualTo(new Score(3, 0));
    }

    @Test
    void Score_계산() {
        assertThat(new Miss(new Pin(1), new Pin(2)).calculateScore(new Score(10, 2)))
                .isEqualTo(new Score(13, 0));
    }

}
