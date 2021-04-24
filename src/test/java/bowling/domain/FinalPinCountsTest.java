package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.BDDAssertions.then;

class FinalPinCountsTest {

    PinCounts finalPinCounts;

    @BeforeEach
    void setUp() {
        finalPinCounts = new FinalPinCounts();
    }

    @Test
    @DisplayName("초구를 던진다.")
    public void knockDown_first() throws Exception {
        finalPinCounts.knockDown(5);
        assertThat(finalPinCounts.pinCounts().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("2구를 던진다.")
    public void knockDown_second() {
        finalPinCounts.knockDown(10);
        finalPinCounts.knockDown(8);
        assertThat(finalPinCounts.totalPinCount()).isEqualTo(18);
    }

    @Test
    @DisplayName("초구가 스트라이크가 아닌데, 초구 + 2구의 합이 10을 넘어갈 경우 예외가 발생한다.")
    public void knockDown_second_exception() {
        finalPinCounts.knockDown(1);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> finalPinCounts.knockDown(10))
                .withMessage("총 쓰러뜨린 개수가 10을 초과하는 지 확인해주세요.");
    }

    @ParameterizedTest
    @CsvSource(value = {"10, 9, 1, 20", "9, 1, 10, 20"})
    @DisplayName("초구 스트라이크 또는 2구 스페어일 경우, 3구를 던진다.")
    public void knockDown_third(int firstPinCount, int secondPinCount, int thirdPinCount, int expected) {
        //given
        finalPinCounts.knockDown(firstPinCount);
        finalPinCounts.knockDown(secondPinCount);

        //when
        finalPinCounts.knockDown(thirdPinCount);

        then(finalPinCounts.totalPinCount()).isEqualTo(expected);
    }

    @Test
    @DisplayName("초구 스트라이크 또는 2구 스페어가 아닌데 3구를 던질 경우, 예외가 발생한다.")
    public void knockDown_third_no_strike_no_spare_exception() {
        finalPinCounts.knockDown(1);
        finalPinCounts.knockDown(2);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> finalPinCounts.knockDown(1))
                .withMessage("초구 스트라이크 또는 2구 스페어가 아니기 때문에 3구를 던질 수 없습니다.");
    }

    @Test
    @DisplayName("초구 스트라이크, 2구 스트라이크가 아닌 상태에서 3구를 던질 경우, 2구, 3구에서 총 쓰러뜨린 개수가 10개를 초과하면 예외가 발생한다.")
    public void knockDown_third_spare_bound_exception() {
        finalPinCounts.knockDown(10);
        finalPinCounts.knockDown(2);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> finalPinCounts.knockDown(9))
                .withMessage("2구, 3구에서 총 쓰러뜨린 개수가 10개를 초과하는 지 확인해주세요.");
    }

    @Test
    @DisplayName("총 쓰러뜨린 핀의 개수를 반환한다.")
    public void totalPinCount() {
        //given
        finalPinCounts.knockDown(10);
        finalPinCounts.knockDown(5);
        finalPinCounts.knockDown(5);

        //when
        int totalPinCount = finalPinCounts.totalPinCount();

        then(totalPinCount).isEqualTo(20);
    }

    @Test
    @DisplayName("3구 기회 없이 2구를 모두 던지거나, 3구 기회가 있으며 3구 모두 던졌을 경우 참을 반환한다.")
    public void isFinished() {
        //given
        PinCounts allThreeThrown = new FinalPinCounts();
        PinCounts allTwoThrown = new FinalPinCounts();
        allThreeThrown.knockDown(10);
        allThreeThrown.knockDown(5);
        allThreeThrown.knockDown(5);
        allTwoThrown.knockDown(5);
        allTwoThrown.knockDown(4);
        finalPinCounts.knockDown(1);

        //when
        boolean allThrownFinished = allThreeThrown.isFinished();
        boolean allTwoThrownFinished = allTwoThrown.isFinished();
        boolean unFinished = finalPinCounts.isFinished();

        then(allThrownFinished).isTrue();
        then(allTwoThrownFinished).isTrue();
        then(unFinished).isFalse();
    }
}