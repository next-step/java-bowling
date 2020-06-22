package bowling.domain.frameResult;

import bowling.domain.NumberOfHitPin;
import bowling.domain.exceptions.InvalidTryBowlException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("초구를 던지지 않은 상태에서 두번재 투구를 던질 경우 예외 발생")
    @Test
    void bowlValidationTest() {
        FinalFrameResult finalFrameResult =
                new FinalFrameResult(null, null, null);

        assertThatThrownBy(() -> finalFrameResult.bowl(FIVE))
                .isInstanceOf(InvalidTryBowlException.class);
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
