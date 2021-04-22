package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.BDDAssertions.then;

class FinalFrameBowlsTest {

    @Test
    @DisplayName("초구를 던진다.")
    public void firstThrow() throws Exception {
        FrameBowls finalFrameBowls = new FinalFrameBowls().firstThrow(5);
        assertThat(finalFrameBowls).isEqualTo(new FinalFrameBowls().firstThrow(5));
    }

    @Test
    @DisplayName("2구를 던진다.")
    public void secondThrow() {
        FrameBowls finalFrameBowls = new FinalFrameBowls().firstThrow(10).secondThrow(8);
        assertThat(finalFrameBowls.totalPinCount()).isEqualTo(18);
    }

    @Test
    @DisplayName("초구가 스트라이크가 아닌데, 초구 + 2구의 합이 10을 넘어갈 경우 예외가 발생한다.")
    public void secondThrow_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new FinalFrameBowls().firstThrow(1).secondThrow(10))
                .withMessage("총 쓰러뜨린 개수가 10을 초과하는 지 확인해주세요.");
    }

    @Test
    @DisplayName("초구 스트라이크 또는 2구 스페어일 경우, 3구를 던진다.")
    public void thirdThrow() {
        //given
        FinalFrameBowls strike = (FinalFrameBowls) new FinalFrameBowls().firstThrow(10).secondThrow(9);
        FinalFrameBowls spare = (FinalFrameBowls) new FinalFrameBowls().firstThrow(9).secondThrow(1);

        //when
        strike = (FinalFrameBowls) strike.thirdThrow(1);
        spare = (FinalFrameBowls) spare.thirdThrow(10);

        then(strike.totalPinCount()).isEqualTo(20);
        then(spare.totalPinCount()).isEqualTo(20);
    }

    @Test
    @DisplayName("초구 스트라이크 또는 2구 스페어가 아닌데 3구를 던질 경우, 예외가 발생한다.")
    public void thirdThrow_no_strike_no_spare_exception() {
        FinalFrameBowls frameBowls = (FinalFrameBowls) new FinalFrameBowls().firstThrow(1).secondThrow(2);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> frameBowls.thirdThrow(1))
                .withMessage("초구 스트라이크 또는 2구 스페어가 아니기 때문에 3구를 던질 수 없습니다.");

    }

    @Test
    @DisplayName("초구 스트라이크, 2구 스트라이크가 아닌 상태에서 3구를 던질 경우, 2구, 3구에서 총 쓰러뜨린 개수가 10개를 초과하면 예외가 발생한다.")
    public void thirdThrow_exception() {
        FinalFrameBowls frameBowls = (FinalFrameBowls) new FinalFrameBowls().firstThrow(10).secondThrow(2);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> frameBowls.thirdThrow(9))
                .withMessage("2구, 3구에서 총 쓰러뜨린 개수가 10개를 초과하는 지 확인해주세요.");

    }

    @Test
    @DisplayName("초구가 스트라이크일 경우, 참을 반환한다.")
    public void isFirstBowlStrike() {
        FrameBowls finalFrameBowls = new FinalFrameBowls().firstThrow(10);
        boolean isStrike = finalFrameBowls.isFirstBowlStrike();
        assertThat(isStrike).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 9", "2, 8"})
    @DisplayName("2구가 스페어일 경우, 참을 반환한다.")
    public void isSecondBowlSpare(int firstPinCount, int secondPinCount) {
        FrameBowls finalFrameBowls = new FinalFrameBowls().firstThrow(firstPinCount).secondThrow(secondPinCount);
        boolean isSpare = finalFrameBowls.isSecondBowlSpare();
        assertThat(isSpare).isTrue();
    }

    @Test
    @DisplayName("2구가 스트라이크일 경우, 참을 반환한다.")
    public void isSecondBowlStrike() {
        FinalFrameBowls finalFrameBowls = (FinalFrameBowls) new FinalFrameBowls().firstThrow(0).secondThrow(10);
        boolean isSrike = finalFrameBowls.isSecondBowlStrike();
        assertThat(isSrike).isTrue();
    }

    @Test
    @DisplayName("총 쓰러뜨린 핀의 개수를 반환한다.")
    public void totalScore() {
        FinalFrameBowls finalFrameBowls = (FinalFrameBowls) new FinalFrameBowls().firstThrow(10).secondThrow(5);
        int totalPinCount = finalFrameBowls.thirdThrow(5).totalPinCount();
        assertThat(totalPinCount).isEqualTo(20);
    }

    @Test
    @DisplayName("첫 공이 던져졌을 경우, 참을 반환한다.")
    public void isFirstBowlThrown() throws Exception {
        //given
        FrameBowls thrownBowl = new FinalFrameBowls().firstThrow(5);
        FrameBowls notThrownBowl = new FinalFrameBowls();

        //when
        boolean Thrown = thrownBowl.isFirstBowlThrown();
        boolean notThrown = notThrownBowl.isFirstBowlThrown();

        then(Thrown).isTrue();
        then(notThrown).isFalse();
    }

    @Test
    @DisplayName("두번째 공이 던져졌을 경우, 참을 반환한다.")
    public void isSecondBowlThrown() throws Exception {
        //given
        FrameBowls thrownBowl = new FinalFrameBowls().firstThrow(5).secondThrow(3);
        FrameBowls notThrownBowl = new FinalFrameBowls().firstThrow(4);

        //when
        boolean Thrown = thrownBowl.isSecondBowlThrown();
        boolean notThrown = notThrownBowl.isSecondBowlThrown();

        then(Thrown).isTrue();
        then(notThrown).isFalse();
    }

    @Test
    @DisplayName("세번째 공이 던져졌을 경우, 참을 반환한다.")
    public void isThirdBowlThrown() throws Exception {
        //given
        FinalFrameBowls thrownBowl = (FinalFrameBowls) new FinalFrameBowls().firstThrow(5).secondThrow(5);
        thrownBowl = (FinalFrameBowls) thrownBowl.thirdThrow(10);

        //when
        boolean Thrown = thrownBowl.isThirdBowlThrown();

        then(Thrown).isTrue();
    }
}