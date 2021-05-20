package bowling.domain.frame;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FinalFrameTest {

    private static final int STRIKE = 10;
    private static final int GUTTER = 0;
    private static final int HALF = 5;

    private FinalFrame finalFrame;

    @BeforeEach
    void setup() {
        finalFrame = new FinalFrame();
    }

    @Test
    @DisplayName("스트라이크일 때 세만에 프레임이 종료된다")
    void strikeGetsTwoExtraChances() {
        finalFrame.bowl(STRIKE);
        assertThat(finalFrame.isEnd()).isFalse();
        finalFrame.bowl(STRIKE);
        assertThat(finalFrame.isEnd()).isFalse();
        finalFrame.bowl(STRIKE);
        assertThat(finalFrame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("스트라이크를 치고 두 번의 시도에서 스트라이크 없이 10을 초과하면 INVALID_SECOND_PIN을 던진다")
    void invalidPinSumAfterStrikeThrowsException() {
        finalFrame.bowl(STRIKE);
        finalFrame.bowl(HALF);
        CustomException customException = assertThrows(CustomException.class, () -> finalFrame.bowl(STRIKE));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_SECOND_PIN);
    }

    @Test
    @DisplayName("스페어일 때는 세번만에 프레임이 종료된다")
    void spareGetsOneExtraChance() {
        finalFrame.bowl(GUTTER);
        finalFrame.bowl(STRIKE);
        assertThat(finalFrame.isEnd()).isFalse();
        finalFrame.bowl(STRIKE);
        assertThat(finalFrame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("보너스점수가 없는 케이스에서 두번만에 프레임이 종료된다")
    void noBonusEndsFrame() {
        finalFrame.bowl(GUTTER);
        finalFrame.bowl(HALF);
        assertThat(finalFrame.isEnd()).isTrue();
    }

}
