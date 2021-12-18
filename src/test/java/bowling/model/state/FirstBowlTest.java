package bowling.model.state;

import bowling.model.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
class FirstBowlTest {

    @ParameterizedTest
    @CsvSource(value = {"5,5", "4,6", "7,3"})
    @DisplayName("2번째 타구 후 쓰러뜨린 핀이 10개면 스페어 객체 반환 테스트")
    void afterBowlSpareTest(int firstPin, int secondPin) {
        FirstBowl firstBowl = new FirstBowl(new Pins(firstPin));
        assertThat(firstBowl.bowl(secondPin)).isEqualTo(new Spare(new Pins(firstPin),new Pins(secondPin)));
    }

    @ParameterizedTest
    @CsvSource(value = {"3,5", "2,7", "1,3"})
    @DisplayName("2번째 타구 후 쓰러뜨릴 핀이 남아있으면 MISS 객체 반환 테스트")
    void afterBowlMissTest(int firstPin, int secondPin) {
        FirstBowl firstBowl = new FirstBowl(new Pins(firstPin));
        assertThat(firstBowl.bowl(secondPin)).isEqualTo(new Miss(new Pins(firstPin),new Pins(secondPin)));
    }
}
