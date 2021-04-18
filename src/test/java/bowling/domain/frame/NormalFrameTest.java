package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTest {


    @Test
    @DisplayName("일반 프레임은 최대 9번 진행할 수 있다.")
    void valid() {
        assertThatThrownBy(() -> new NormalFrame(9))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("다음 프레임으로 가는 지 확인")
    void next() {
        NormalFrame frame = NormalFrame.createFirst();
        NormalFrame nextFrame = frame.next();
        assertThat(nextFrame).isEqualTo(new NormalFrame(1));
    }


    @Test
    @DisplayName("첫 프레임에 스트라이크를 쳤을 경우 확인")
    void playStrike() {
        NormalFrame frame = NormalFrame.createFirst();
        frame.play(10);
        assertThat(frame.getFallenPins()).isEqualTo("X");
    }


    @Test
    @DisplayName("첫 프레임에 스페어를 쳤을 경우 확인")
    void playSpare() {
        NormalFrame frame = NormalFrame.createFirst();
        frame.play(7);
        frame.play(3);

        assertThat(frame.getFallenPins()).isEqualTo("7|/");
    }


    @Test
    @DisplayName("Normal 프레임에서는 투구 기회가 2번뿐이다.")
    void playOnlyTwoCount() {
        NormalFrame frame = NormalFrame.createFirst();
        frame.play(3);
        frame.play(4);

        assertThatThrownBy(() -> frame.play(3))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("첫 프레임에 스트라이크를 쳤을 경우 더이상 해당 프레임에서는 투구 기회가 없다.")
    void playOnelyOneCountIfStrike() {
        NormalFrame frame = NormalFrame.createFirst();
        frame.play(10);

        assertThatThrownBy(() -> frame.play(2))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @CsvSource(value = {"7, 3, 10, false", "4, 2, 6, true"})
    @DisplayName("점수 계산 확인")
    void calculate(int first, int second, int expectScore, boolean expectHasScore) {
        NormalFrame frame = NormalFrame.createFirst();
        frame.play(first);
        frame.play(second);

        assertThat(frame.getScore()).isEqualTo(expectScore);
        assertThat(frame.hasScore()).isEqualTo(expectHasScore);
    }


    @Test
    @DisplayName("점수 계산 스트라이크 일때 확인")
    void calculateIfStrike() {
        NormalFrame frame = NormalFrame.createFirst();
        frame.play(10);

        assertThat(frame.getScore()).isEqualTo(10);
        assertThat(frame.hasScore()).isEqualTo(false);
    }
}
