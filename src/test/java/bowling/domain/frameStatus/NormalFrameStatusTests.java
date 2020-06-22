package bowling.domain.frameStatus;

import bowling.domain.NumberOfHitPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameStatusTests {
    private static final int TEN = 10;
    private static final int FIVE = 5;

    @DisplayName("초구로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        NormalFrameStatus normalFrameResult = NormalFrameStatus.bowlFirst(TEN);

        assertThat(normalFrameResult).isNotNull();
        assertThat(normalFrameResult.isCompleted()).isTrue();
    }

    @DisplayName("두번째 투구로 새로운 객체를 생성할 수 있다.")
    @Test
    void bowlTwiceTest() {
        NormalFrameStatus normalFrameResult = NormalFrameStatus.bowlFirst(FIVE);
        assertThat(normalFrameResult.isCompleted()).isFalse();

        NormalFrameStatus secondBowled = normalFrameResult.bowl(FIVE);
        assertThat(secondBowled)
                .isEqualTo(new NormalFrameStatus(new NumberOfHitPin(FIVE), new NumberOfHitPin(FIVE)));
        assertThat(secondBowled.isCompleted()).isTrue();
    }

    @DisplayName("스트라이크 여부를 알 수 있다.")
    @Test
    void checkStrikeTest() {
        NormalFrameStatus normalFrameResult = NormalFrameStatus.bowlFirst(TEN);

        assertThat(normalFrameResult.isStrike()).isTrue();
    }

    @DisplayName("스페어 여부를 알 수 있다.")
    @Test
    void checkSpareTest() {
        NormalFrameStatus normalFrameResult = NormalFrameStatus.bowlFirst(FIVE);
        assertThat(normalFrameResult.isSpare()).isFalse();

        NormalFrameStatus secondBowled = normalFrameResult.bowl(FIVE);
        assertThat(secondBowled.isSpare()).isTrue();
    }
}
