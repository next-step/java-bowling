package bowling.domain.frameResult;

import bowling.domain.NumberOfHitPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameResultTests {
    private static final int TEN = 10;
    private static final int FIVE = 5;

    @DisplayName("초구로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        NormalFrameResult normalFrameResult = NormalFrameResult.bowlFirst(TEN);

        assertThat(normalFrameResult).isNotNull();
        assertThat(normalFrameResult.isCompleted()).isTrue();
    }

    @DisplayName("두번째 투구로 새로운 객체를 생성할 수 있다.")
    @Test
    void bowlTwiceTest() {
        NormalFrameResult normalFrameResult = NormalFrameResult.bowlFirst(FIVE);
        assertThat(normalFrameResult.isCompleted()).isFalse();

        NormalFrameResult secondBowled = normalFrameResult.bowlSecond(FIVE);
        assertThat(secondBowled)
                .isEqualTo(new NormalFrameResult(new NumberOfHitPin(FIVE), new NumberOfHitPin(FIVE)));
        assertThat(secondBowled.isCompleted()).isTrue();
    }

    @DisplayName("스트라이크 여부를 알 수 있다.")
    @Test
    void checkStrikeTest() {
        NormalFrameResult normalFrameResult = NormalFrameResult.bowlFirst(TEN);

        assertThat(normalFrameResult.isStrike()).isTrue();
    }

    @DisplayName("스페어 여부를 알 수 있다.")
    @Test
    void checkSpareTest() {
        NormalFrameResult normalFrameResult = NormalFrameResult.bowlFirst(FIVE);
        assertThat(normalFrameResult.isSpare()).isFalse();

        NormalFrameResult secondBowled = normalFrameResult.bowlSecond(FIVE);
        assertThat(secondBowled.isSpare()).isTrue();
    }
}
