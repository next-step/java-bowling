package bowling.domain.frame;

import bowling.exception.UnableCreateFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FinalFrameTest {

    private final int bonusCount = 10;

    @ParameterizedTest(name = "보너스 게임 - 1차 : {0}, 2차 : {1}")
    @CsvSource(delimiter = '|', value = {"5|5", "10|0", "0|10"})
    void bonusFrame(String first, String second) {
        int firstCount = Integer.parseInt(first);
        int secondCount = Integer.parseInt(second);

        Frame finalFrame = FinalFrame.lastBowling(firstCount).bowling(secondCount);
        Frame bonusFrame = finalFrame.bowling(this.bonusCount);
        assertAll(() -> assertThat(bonusFrame.totalCount()).isEqualTo(firstCount + secondCount + this.bonusCount),
                () -> assertThat(bonusFrame.isFinishBowling()).isTrue());

    }

    @Test
    @DisplayName("보너스 게임일 때 다음 프레임 생성 혹은 투구하는 경우 예외 처리")
    void isFinalException() {
        int firstCount = 10;
        int secondCount = 0;

        Frame finalFrame = FinalFrame.lastBowling(firstCount).bowling(secondCount);

        assertThatThrownBy(() -> finalFrame.next(this.bonusCount)).isExactlyInstanceOf(UnableCreateFrameException.class);
    }
}
