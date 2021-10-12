package bowling.domain.state;

import bowling.domain.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class OncePitchedTest {

    @DisplayName("Input 값이 Max Pins인 경우 IllegalArgumentException 발생")
    @Test
    void create_ThrowsIllegalArgumentException_IfInputIsMaxPinCount() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new OncePitched(PinCount.MAX_PINS)
        );
    }

    @DisplayName("입력 핀 카운트와 OncePitched 핀 카운트 합이 10일 경우 다음 상태 스페어 반환")
    @Test
    void play_ReturnSpare_IfFirstAndSecondPinCountSumIsTen() {
        State oncePitched = StateFactory.play(PinCount.of(3));
        State spare = oncePitched.play(PinCount.of(7));
        assertThat(spare).isExactlyInstanceOf(Spare.class);
    }

    @DisplayName("입력 핀 카운트와 OncePitched 핀 카운트 합이 10이 아닐 경우 다음 상태 미 반환")
    @Test
    void play_ReturnMiss_IfFirstAndSecondPinCountSumIsNotTen() {
        State oncePitched = StateFactory.play(PinCount.of(3));
        State spare = oncePitched.play(PinCount.of(3));
        assertThat(spare).isExactlyInstanceOf(Miss.class);
    }

}