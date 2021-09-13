package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("마지막 볼링 프레임 테스트")
public class FinalFrameTest {

    @DisplayName("프레임 번호가 10이 아니면 예외가 발생한다.")
    @Test
    void outOfRangeFinalFrameNumberTest() {
        // given
        int frameNumber = 9;
        int firstFallenPinCount = 5;
        int secondFallenPinCount = 5;
        int score = 10;
        int remainingPitchingCount = 1;
        int bonusFallenPinCount = 0;

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new FinalFrame(frameNumber, firstFallenPinCount, secondFallenPinCount, score,
                        remainingPitchingCount, bonusFallenPinCount))
                .withMessage("마지막 프레임 번호는 10 이어야 합니다.");
    }

    @DisplayName("두 번 공을 던지고 스트라이크나 스페어가 아닌데, 다음 프레임을 생성하면 예외가 발생한다.")
    @Test
    void nextFinalFrameWhenPitchTwiceAndNotStrikeAndNotSpareExceptionTest() {
        // given
        int frameNumber = 10;
        int firstFallenPinCount = 5;
        int secondFallenPinCount = 3;
        int score = 8;
        int remainingPitchingCount = 0;
        int bonusFallenPinCount = 0;

        Frame notStrikeAndNotSpare = new FinalFrame(frameNumber, firstFallenPinCount, secondFallenPinCount, score,
                remainingPitchingCount, bonusFallenPinCount);

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> notStrikeAndNotSpare.next(5))
                .withMessage("마지막 프레임의 다음 번호 프레임을 만들 수 없습니다.");
    }

    @DisplayName("공을 던지고 스트라이크나 스페어면 다음 프레임을 생성할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"10:10:0:10:2:0", "10:5:5:10:1:0"}, delimiter = ':')
    void nextFinalFrameWhenPitchTwiceAndStrikeOrSpareTest(int frameNumber, int firstFallenPinCount, int secondFallenPinCount,
                                                          int score, int remainingPitchingCount, int bonusFallenPinCount) {
        // given
        Frame finalFrame = new FinalFrame(frameNumber, firstFallenPinCount, secondFallenPinCount, score,
                remainingPitchingCount, bonusFallenPinCount);

        // when, then
        assertThat(finalFrame.next(5)).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("공을 한 번 던진 상태에서 다음 프레임을 생성할 수 있다.")
    @Test
    void nextFinalFrameWhenOnePitchTest() {
        // given
        int frameNumber = 10;
        int firstFallenPinCount = 5;
        int score = 5;
        int remainingPitchingCount = 0;

        Frame oncePitchFinalFrame = new FinalFrame(frameNumber, firstFallenPinCount, score, remainingPitchingCount,
                remainingPitchingCount);

        // when, then
        assertThat(oncePitchFinalFrame.next(5)).isInstanceOf(FinalFrame.class);
    }
}
