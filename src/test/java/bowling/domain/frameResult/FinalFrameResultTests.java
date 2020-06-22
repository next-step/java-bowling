package bowling.domain.frameResult;

import bowling.domain.NumberOfHitPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameResultTests {
    private static final int FIVE = 5;
    private static final int TEN = 10;

    @DisplayName("초구를 던져서 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        FinalFrameResult finalFrameResult = FinalFrameResult.bowlFirst(FIVE);

        assertThat(finalFrameResult)
                .isEqualTo(new FinalFrameResult(
                        new NumberOfHitPin(FIVE), null, null));
    }

    @DisplayName("초구가 스트라이크면 무조건 3회 투구해야 해당 프레임 결과가 마무리된다.")
    @Test
    void completeWhenFirstStrike() {
        FinalFrameResult finalFrameResult = FinalFrameResult.bowlFirst(TEN);
        assertThat(finalFrameResult.isCompleted()).isFalse();

        FinalFrameResult afterSecond = finalFrameResult.bowl(FIVE);
        assertThat(afterSecond.isCompleted()).isFalse();

        FinalFrameResult afterThird = afterSecond.bowl(FIVE);
        assertThat(afterThird.isCompleted()).isTrue();
    }
}
