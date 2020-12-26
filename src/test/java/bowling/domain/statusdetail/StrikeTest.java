package bowling.domain.statusdetail;

import bowling.domain.frame.DownedPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    @DisplayName("스트라이크 상태 출력")
    void interpretFrame() {
        List<DownedPin> pins = new ArrayList<>();
        DownedPin strike = DownedPin.fromNumber(10);

        pins.add(strike);

        assertThat(new Strike().interpretFrame(pins))
                .isEqualTo(" X ");
    }
}
