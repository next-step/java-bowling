package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.Assert.assertTrue;
class PinsTest {

    @ParameterizedTest
    @ValueSource(ints = { -1, -2, 11, 12})
    @DisplayName("투구 값이 0보다 작거나 10보다 크면 예외 발생")
    void knockedDownPinUnderZeroOrOverTenExceptionTest(int knockedDownPin) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Pins.knockedDown(knockedDownPin))
                .withMessage("투구 값은 0과 10 사이의 값이어야 합니다.");
    }

    @Test
    @DisplayName("투구가 10이면 스트라이크")
    void isStrikeTest(){
        assertTrue(Pins.knockedDown(10).isStrike());
    }

    @ParameterizedTest
    @CsvSource(value = {"2,8,true","3,7,true", "1,5,false"})
    @DisplayName("두번째 투구에서 10개를 쓰러트리면 스페어 테스트")
    void isSpareTest(int knockedDownPin1, int knockedDownPin2, boolean isSpare){
        assertThat(Pins.knockedDown(knockedDownPin2).isSpare(Pins.knockedDown(knockedDownPin1))).isEqualTo(isSpare);
    }

    @ParameterizedTest
    @CsvSource(value = {"2,9","3,10"})
    @DisplayName("두번째 투구로 쓰러진 핀 개수가 10을 넘으면 예외가 발생한다.")
    void sumOverMaxPinCountThrowException(int knockedDownPin1, int knockedDownPin2){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Pins.knockedDown(knockedDownPin2).isSpare(Pins.knockedDown(knockedDownPin1)))
                .withMessage("투구로 쓰러진 핀의 총 개수는 10개를 넘을 수 없습니다.");
    }
}
