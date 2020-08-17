package bowling.domain.frame;

import bowling.domian.frame.FinalFrame;
import bowling.domian.frame.exception.FrameEndException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FinalFrameTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9})
    @DisplayName("1회 Miss 투구 시 프레임 투구 가능")
    public void bowlPossibleWhenMissOneTime(int falledPinsCount) {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.bowl(falledPinsCount);

        assertThat(finalFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("2회 투구 결과 Miss 프레임 종료")
    public void bowlDoneWhenMissTwoTimes() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.bowl(4);
        finalFrame.bowl(5);

        assertThat(finalFrame.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("Spare 시 보너스 프레임 가능")
    public void bonusBowlPossibleWhenSpare() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.bowl(4);
        finalFrame.bowl(6);

        assertThat(finalFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("Strike 시 보너스 프레임 가능")
    public void bonusBowlPossibleWhenStrike() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.bowl(10);

        assertThat(finalFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("보너스 투구 시 Strike여도 프레임 종료")
    public void bonusBowlOnlyOneTime() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.bowl(10);
        finalFrame.bowl(10);

        assertThat(finalFrame.isGameEnd()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"0,9,5", "10,10,5"})
    @DisplayName("프레임의 투구가 끝난 후 투구 시도 시 Exception")
    public void exceptionWhenFrameEnd(int first, int second, int third) {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.bowl(first);
        finalFrame.bowl(second);

        assertThatExceptionOfType(FrameEndException.class).isThrownBy(
                () -> finalFrame.bowl(third)
        );
    }
}
