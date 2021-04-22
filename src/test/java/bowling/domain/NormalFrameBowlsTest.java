package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.then;

public class NormalFrameBowlsTest {

    @Test
    @DisplayName("초구에 쓰러뜨린 결과를 생성한다.")
    public void firstThrow() {
        FrameBowls normalFrameBowls = new NormalFrameBowls().firstThrow(0);
        assertThat(normalFrameBowls).isEqualTo(new NormalFrameBowls().firstThrow(0));
    }

    @Test
    @DisplayName("초구가 스트라이크가 아닐 경우, 2구를 던진다.")
    public void secondThrow() {
        FrameBowls normalFrameBowls = new NormalFrameBowls().firstThrow(1).secondThrow(8);
        assertThat(normalFrameBowls.totalPinCount()).isEqualTo(9);
    }

    @Test
    @DisplayName("초구가 스트라이크인데 2구를 던지려고 할 경우, 예외가 발생한다.")
    public void secondThrow_exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new NormalFrameBowls().firstThrow(10).secondThrow(8))
                .withMessage("초구가 스트라이크이기 때문에 2구를 던질 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 10", "2, 9"})
    @DisplayName("총 쓰러뜨린 개수가 10을 넘어갈 경우 예외가 발생한다.")
    public void checkBound(int firstPinCount, int secondPincount) {
        assertThatIllegalArgumentException().isThrownBy(()
                -> new NormalFrameBowls().firstThrow(firstPinCount).secondThrow(secondPincount))
                .withMessage("총 쓰러뜨린 개수가 10을 초과하는 지 확인해주세요.");
    }

    @Test
    @DisplayName("스트라이크일 경우, 참을 반환한다.")
    public void isFirstBowlStrike() {
        FrameBowls normalFrameBowls = new NormalFrameBowls().firstThrow(10);
        boolean isStrike = normalFrameBowls.isFirstBowlStrike();
        assertThat(isStrike).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 9", "2, 8"})
    @DisplayName("스페어일 경우, 참을 반환한다.")
    public void isSecondBowlSpare(int firstPinCount, int secondPinCount) {
        FrameBowls normalFrameBowls = new NormalFrameBowls().firstThrow(firstPinCount).secondThrow(secondPinCount);
        boolean isSpare = normalFrameBowls.isSecondBowlSpare();
        assertThat(isSpare).isTrue();
    }

    @Test
    @DisplayName("총 쓰러뜨린 핀의 개수를 반환한다.")
    public void totalScore() {
        FrameBowls normalFrameBowls = new NormalFrameBowls().firstThrow(5).secondThrow(5);
        int totalPinCount = normalFrameBowls.totalPinCount();
        assertThat(totalPinCount).isEqualTo(10);
    }

    @Test
    @DisplayName("첫 공이 던져졌을 경우, 참을 반환한다.")
    public void isFirstBowlThrown() throws Exception {
        //given
        FrameBowls thrownBowl = new NormalFrameBowls().firstThrow(5);
        NormalFrameBowls notThrownBowl = new NormalFrameBowls();

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
        FrameBowls thrownBowl = new NormalFrameBowls().firstThrow(5).secondThrow(3);
        FrameBowls notThrownBowl = new NormalFrameBowls().firstThrow(4);

        //when
        boolean Thrown = thrownBowl.isSecondBowlThrown();
        boolean notThrown = notThrownBowl.isSecondBowlThrown();

        then(Thrown).isTrue();
        then(notThrown).isFalse();
    }
}
