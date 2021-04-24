package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.BDDAssertions.then;

class NormalPinCountsTest {

    PinCounts normalPinCounts;

    @BeforeEach
    void setUp() {
        normalPinCounts = new NormalPinCounts();
    }

    @Test
    @DisplayName("초구에 쓰러뜨린 결과를 생성한다.")
    public void knockDown_first() {
        //when
        normalPinCounts.knockDown(1);

        then(normalPinCounts.pinCounts().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("초구가 스트라이크가 아닐 경우, 2구를 던진다.")
    public void knockDown_second() {
        //when
        normalPinCounts.knockDown(0);
        normalPinCounts.knockDown(1);

        then(normalPinCounts.pinCounts().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("초구가 스트라이크인데 2구를 던지려고 할 경우, 예외가 발생한다.")
    public void knockDown_second_exception() {
        normalPinCounts.knockDown(10);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> normalPinCounts.knockDown(10))
                .withMessage("초구가 스트라이크이기 때문에 2구를 던질 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 10", "2, 9"})
    @DisplayName("총 쓰러뜨린 개수가 10을 넘어갈 경우 예외가 발생한다.")
    public void checkSpareBound(int firstPinCount, int secondPincount) {
        normalPinCounts.knockDown(firstPinCount);

        assertThatIllegalArgumentException().isThrownBy(()
                -> normalPinCounts.knockDown(secondPincount))
                .withMessage("총 쓰러뜨린 개수가 10을 초과하는 지 확인해주세요.");
    }

    @Test
    @DisplayName("총 쓰러뜨린 핀의 개수를 반환한다.")
    public void totalPinCount() {
        normalPinCounts.knockDown(5);
        normalPinCounts.knockDown(5);

        //when
        int totalPinCount = normalPinCounts.totalPinCount();

        then(totalPinCount).isEqualTo(10);
    }

    @Test
    @DisplayName("모든 공을 던지거나, 초구가 스트라이크일 경우, 참을 반환한다.")
    public void isFinished() {
        //given
        PinCounts allThrown = new NormalPinCounts();
        PinCounts strike = new NormalPinCounts();
        normalPinCounts.knockDown(1);
        allThrown.knockDown(5);
        allThrown.knockDown(5);
        strike.knockDown(10);

        //when
        boolean allThrownFinished = allThrown.isFinished();
        boolean strikeFinished = strike.isFinished();
        boolean unFinished = normalPinCounts.isFinished();

        then(allThrownFinished).isTrue();
        then(strikeFinished).isTrue();
        then(unFinished).isFalse();
    }
}