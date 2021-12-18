package bowling.domain.bowl;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalBowlTest {

    Bowl bowl = new FinalBowl();

    @DisplayName("스트라이크나 스페어를 치면 투구를 세번까지 할 수 있다.")
    @ParameterizedTest(name = "[{index}] first: {0}, second: {1}, third: {2}")
    @CsvSource({
            "10, 10, 10",
            "0, 10, 10",
            "2, 8, 0",
            "7, 3, 5",
            "10, 0, 1",
            "0, 10, 6",
            "3, 7, 0",
            "1, 9, 3",
            "5, 5, 5"
    })
    void pitch_strikeOrSpare_getThreeChance(int first, int second, int third) {
        bowl.pitch(pin(first));
        bowl.pitch(pin(second));
        bowl.pitch(pin(third));
        verifyNoChanceLeft();
    }

    @DisplayName("스트라이크나 스페어가 아니라면 투구를 두번만 할수 있다.")
    @ParameterizedTest(name = "[{index}] first: {0}, second: {1}")
    @CsvSource({
            "1, 3",
            "0, 0",
            "5, 4"
    })
    void pitch_neitherStrikeNorSpare_getTwoChance(int first, int second) {
        bowl.pitch(pin(first));
        bowl.pitch(pin(second));
        verifyNoChanceLeft();
    }

    private Pin pin(int hitCount) {
        return Pin.from(hitCount);
    }

    private void verifyNoChanceLeft() {
        Pin pin = pin(1);
        assertThat(bowl.canPitch()).isFalse();
        assertThatThrownBy(() -> bowl.pitch(pin))
                .isInstanceOf(CanNotPitchException.class);
    }

}
