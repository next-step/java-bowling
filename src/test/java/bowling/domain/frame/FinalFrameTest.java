package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {

    @Test
    @DisplayName("3개, 4개 친 경우 2번의 기회가 있다.")
    void playTwoCount() {
        FinalFrame frame = new FinalFrame();
        frame.play(3);
        frame.play(4);

        assertThat(frame.getFallenPins()).isEqualTo("3|4");
    }


    @ParameterizedTest
    @CsvSource(value = {"3, 7, 1, 3|/|1", "10, 10, 10, X|X|X", "10, 4, 1, X|4|1"})
    @DisplayName("스트라이크나 스페어인경우 3번의 기회가 있다.")
    void playThreeCount(int first, int second, int third, String expect) {
        FinalFrame frame = new FinalFrame();
        frame.play(first);
        frame.play(second);
        frame.play(third);

        assertThat(frame.getFallenPins()).isEqualTo(expect);
    }


    @Test
    @DisplayName("스트라이크나 스페어가 아닌 경우 3번째 투구를 못친다.")
    void valid() {
        FinalFrame frame = new FinalFrame();
        frame.play(3);
        frame.play(4);

        assertThatThrownBy(() -> frame.play(10))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("최대 3번의 투구 기회만 있다.")
    void validMaxThreeCount() {
        FinalFrame frame = new FinalFrame();
        frame.play(10);
        frame.play(10);
        frame.play(10);

        assertThatThrownBy(() -> frame.play(10))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("10개 핀 이상 쓰러트린 경우 확인")
    void validTen() {
        FinalFrame frame = new FinalFrame();
        frame.play(8);

        assertThatThrownBy(() -> frame.play(10))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @CsvSource(value = {"10, 10, 10, 30", "2, 8, 3, 13"})
    @DisplayName("3번 투구한 경우 점수 확인")
    void calculate(int first, int second, int third, int expectScore) {
        FinalFrame frame = new FinalFrame();
        frame.play(first);
        frame.play(second);
        frame.play(third);

        assertThat(frame.getScore()).isEqualTo(expectScore);
        assertThat(frame.hasScore()).isTrue();
    }


    @ParameterizedTest
    @CsvSource(value = {"5, 5, 10", "3, 4, 7"})
    @DisplayName("2번 투구한 경우 점수 확인")
    void calculateTwoCount(int first, int second, int expectScore) {
        FinalFrame frame = new FinalFrame();
        frame.play(first);
        frame.play(second);

        assertThat(frame.getScore()).isEqualTo(expectScore);
    }
}
