package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("마지막 볼링 프레임 테스트")
public class FinalFrameTest {

    @DisplayName("프레임 번호가 10이 아니면 예외가 발생한다.")
    @Test
    void outOfRangeFinalFrameNumberTest() {
        // given
        int frameNumber = 9;

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new FinalFrame(frameNumber, 5, 5, 10, 1, 0))
                .withMessage("마지막 프레임 번호는 10 이어야 합니다.");
    }

    @DisplayName("두 번 공을 던지고 스트라이크나 스페어가 아닌데, 다음 프레임을 생성하면 예외가 발생한다.")
    @Test
    void nextFinalFrameWhenPitchTwiceAndNotStrikeAndNotSpareExceptionTest() {
        // given
        Frame notStrikeAndNotSpare = new FinalFrame(10, 5, 3, 8, 0, 0);

        // when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> notStrikeAndNotSpare.next(5))
                .withMessage("마지막 프레임의 다음 번호 프레임을 만들 수 없습니다.");
    }

    @DisplayName("공을 던지고 스트라이크나 스페어면 다음 프레임을 생성할 수 있다.")
    @Test
    void nextFinalFrameWhenPitchTwiceAndStrikeOrSpareTest() {
        // given
        Frame strikeFinalFrame = new FinalFrame(10, 10, 10, 2, 0);
        Frame spareFinalFrame = new FinalFrame(10, 5, 5, 10, 1, 0);

        // when, then
        assertThat(strikeFinalFrame.next(5)).isInstanceOf(FinalFrame.class);
        assertThat(spareFinalFrame.next(5)).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("공을 한 번 던진 상태에서 다음 프레임을 생성할 수 있다.")
    @Test
    void nextFinalFrameWhenOnePitchTest() {
        // given
        Frame oncePitchFinalFrame = new FinalFrame(10, 5, 5, 0, 0);

        // when, then
        assertThat(oncePitchFinalFrame.next(5)).isInstanceOf(FinalFrame.class);
    }
}
