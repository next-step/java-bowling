package bowling.model.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FirstBowlTest {

    @ParameterizedTest
    @CsvSource(value = {"6,5", "9,7"})
    @DisplayName("2번째 타구 까지 쓰러뜨린 핀이 10이 넘으면 예외발생 테스트")
    void pinSumOverTenExceptionTest(int firstPin, int secondPin) {
        FirstBowl firstBowl = new FirstBowl(Pins.knockedDown(firstPin));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> firstBowl.bowl(Pins.knockedDown(secondPin)))
                .withMessage("투구로 쓰러진 핀의 총 개수는 10개를 넘을 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"5,5", "4,6", "7,3"})
    @DisplayName("2번째 타구 후 쓰러뜨린 핀이 10개면 스페어 객체 반환 테스트")
    void afterBowlSpareTest(int firstPin, int secondPin) {
        FirstBowl firstBowl = new FirstBowl(Pins.knockedDown(firstPin));
        assertThat(firstBowl.bowl(Pins.knockedDown(secondPin)))
                .isEqualTo(new Spare(Pins.knockedDown(firstPin),Pins.knockedDown(secondPin)));
    }

    @ParameterizedTest
    @CsvSource(value = {"3,5", "2,7", "1,3"})
    @DisplayName("2번째 타구 후 쓰러뜨릴 핀이 남아있으면 MISS 객체 반환 테스트")
    void afterBowlMissTest(int firstPin, int secondPin) {
        FirstBowl firstBowl = new FirstBowl(Pins.knockedDown(firstPin));
        assertThat(firstBowl.bowl(Pins.knockedDown(secondPin)))
                .isEqualTo(new Miss(Pins.knockedDown(firstPin),Pins.knockedDown(secondPin)));
    }
}
