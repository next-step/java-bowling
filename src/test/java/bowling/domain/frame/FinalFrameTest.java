package bowling.domain.frame;

import bowling.domain.frame.exception.UnableBowlingException;
import bowling.domain.frame.exception.UnableCreateFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {

    private final int bonusPins = 10;

    @ParameterizedTest(name = "보너스 게임 - 1차 : {0}, 2차 : {1}")
    @CsvSource(delimiter = '|', value = {"5|5", "10|0", "0|10"})
    void bonusFrame(String first, String second) {
        int firstPins = Integer.parseInt(first);
        int secondPins = Integer.parseInt(second);

        Frame finalFrame = FinalFrame.lastBowling(firstPins).bowling(secondPins);
        Frame bonusFrame = finalFrame.bowling(this.bonusPins);

        assertThat(bonusFrame.isFinishBowling()).isTrue();

    }

    @Test
    @DisplayName("보너스 게임일 때 다음 프레임 생성하는 경우 예외 처리")
    void createException() {
        Frame bonusGame = FinalFrame.lastBowling(10).next(this.bonusPins).next(this.bonusPins);
        assertThatThrownBy(() -> bonusGame.next(this.bonusPins)).isExactlyInstanceOf(UnableCreateFrameException.class);
    }

    @Test
    @DisplayName("보너스 게임일 때 투구하는 경우 예외 처리")
    void bowlingException() {
        Frame bonusGame = FinalFrame.lastBowling(5).bowling(5).next(this.bonusPins);
        assertThatThrownBy(() -> bonusGame.bowling(this.bonusPins)).isExactlyInstanceOf(UnableBowlingException.class);
    }
}
