package bowling.domain.frame;

import bowling.domain.exceptions.InvalidTryBowlException;
import bowling.domain.frameStatus.FinalFrameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTests {
    private static final int TEN = 10;
    private static final int FIVE = 5;
    private static final int FOUR = 4;
    private static final NormalFrame NINTH_FRAME = NormalFrame.start(FIVE).bowl(FIVE);

    @DisplayName("초구로 맞춘 핀의 수와 아홉번째 프레임을 입력 받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        FinalFrame finalFrame = FinalFrame.firstBowl(FIVE, NINTH_FRAME);

        assertThat(finalFrame).isEqualTo(new FinalFrame(10, FinalFrameStatus.bowlFirst(FIVE), NINTH_FRAME));
    }

    @DisplayName("종료될 때까지 마지막 프레임을 진행할 수 있다.")
    @Test
    void bowlTest() {
        FinalFrame finalFrame = FinalFrame.firstBowl(FIVE, NINTH_FRAME);
        assertThat(finalFrame.isCompleted()).isFalse();

        FinalFrame secondBowled = finalFrame.bowl(FIVE);
        assertThat(secondBowled.isCompleted()).isFalse();

        FinalFrame thirdBowled = secondBowled.bowl(TEN);
        assertThat(thirdBowled.isCompleted()).isTrue();
    }

    @DisplayName("종료된 이후에는 프레임을 진행할 수 없다.")
    @Test
    void bowlValidationTest() {
        FinalFrame finalFrame = FinalFrame.firstBowl(FIVE, NINTH_FRAME);
        assertThat(finalFrame.isCompleted()).isFalse();

        FinalFrame secondBowled = finalFrame.bowl(FOUR);
        assertThat(secondBowled.isCompleted()).isTrue();

        assertThatThrownBy(() -> secondBowled.bowl(FIVE))
                .isInstanceOf(InvalidTryBowlException.class);
    }
}
