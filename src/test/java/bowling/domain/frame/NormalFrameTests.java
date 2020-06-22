package bowling.domain.frame;

import bowling.domain.exceptions.InvalidTryBowlException;
import bowling.domain.exceptions.InvalidTryNextFrameException;
import bowling.domain.frameStatus.NormalFrameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTests {
    private static final int FIVE = 5;
    private static final int TEN = 10;

    @DisplayName("초구로 맞춘 핀의 수를 입력받아서 해당 프레임을 시작할 수 있다.")
    @Test
    void createTest() {
        NormalFrame normalFrame = NormalFrame.start(FIVE);

        assertThat(normalFrame)
                .isEqualTo(new NormalFrame(1, NormalFrameStatus.bowlFirst(FIVE), null));
    }

    @DisplayName("현재 프레임이 마무리되지 않은 상태에서 해당 프레임의 다음 투구를 진행할 수 있다.")
    @Test
    void doInThisFrameTest() {
        NormalFrame normalFrame = NormalFrame.start(FIVE);
        NormalFrame afterSecondBowl = normalFrame.bowl(FIVE);

        assertThat(afterSecondBowl)
                .isEqualTo(new NormalFrame(1, NormalFrameStatus.bowlFirst(FIVE).bowl(FIVE), null));
    }

    @DisplayName("현재 프레임이 마무리 된 상태에서 해당 프레임의 다음 투구를 진행할 수 없다.")
    @Test
    void doInThisFrameValidationTest() {
        NormalFrame normalFrame = NormalFrame.start(TEN);

        assertThatThrownBy(() -> normalFrame.bowl(FIVE))
                .isInstanceOf(InvalidTryBowlException.class);
    }

    @DisplayName("현재 프레임이 마무리 된 상태에서 다음 프레임을 시작할 수 있다.")
    @Test
    void nextFrameTest() {
        NormalFrame normalFrame = NormalFrame.start(TEN);

        assertThat(normalFrame.next(FIVE))
                .isEqualTo(new NormalFrame(2, NormalFrameStatus.bowlFirst(FIVE), normalFrame));
    }

    @DisplayName("현재 프레임이 마무리 되지 않은 상태에서 다음 프레임을 시작할 수 없다.")
    @Test
    void nextFrameValidationTest() {
        NormalFrame normalFrame = NormalFrame.start(FIVE);

        assertThatThrownBy(() -> normalFrame.next(FIVE))
                .isInstanceOf(InvalidTryNextFrameException.class);
    }
}
