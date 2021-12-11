package bowling.domain.frame;

import bowling.domain.bowl.CanNotPitchException;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    Frame finalFrame;

    @BeforeEach
    void initFrame() {
        finalFrame = new FinalFrame();
    }

    @DisplayName("10프레임은 다음 프레임이 없다")
    @Test
    void next_notSupported() {
        assertThat(finalFrame.hasNextFrame()).isFalse();
        assertThatThrownBy(() -> finalFrame.next())
                .isInstanceOf(NoNextFrameException.class);
    }

    @DisplayName("스트라이크나 스페어를 치면 투구를 세번까지 할 수 있다.")
    @ParameterizedTest(name = "[{index}] first: {0}, second: {1}, third: {2}")
    @CsvSource({
            "10, 10, 10",
            "0, 10, 10",
            "2, 8, 0",
            "7, 3, 5"
    })
    void pitch_strikeOrSpare_getThreeChance(int first, int second, int third) {
        finalFrame.pitch(pin(first));
        finalFrame.pitch(pin(second));
        finalFrame.pitch(pin(third));
        verifyNoNextChance();
    }

    @DisplayName("스트라이크나 스페어가 아니라면 투구를 두번만 할수 있다.")
    @ParameterizedTest(name = "[{index}] first: {0}, second: {1}")
    @CsvSource({
            "1, 3",
            "0, 0",
            "5, 4"
    })
    void pitch_neitherStrikeNorSpare_getTwoChance(int first, int second) {
        finalFrame.pitch(pin(first));
        finalFrame.pitch(pin(second));
        verifyNoNextChance();
    }

    private Pin pin(int hitCount) {
        return Pin.from(hitCount);
    }

    private void verifyNoNextChance() {
        assertThat(finalFrame.hasNextFrame()).isFalse();

        Pin pin = pin(1);
        assertThatThrownBy(() -> finalFrame.pitch(pin))
                .isInstanceOf(CanNotPitchException.class);
    }

}
