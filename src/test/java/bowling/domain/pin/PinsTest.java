package bowling.domain.pin;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.ScoreType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class PinsTest {

    @DisplayName("쓰러뜨린 핀 갯수 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void addTest(int pin) {
        // given
        Pins pins = new Pins();
        Frame frame = NormalFrame.generateNormalFrame();
        // when
        pins.addPins(frame, pin);

        // then
        assertThat(pins.getTotalPins()).isEqualTo(pin);
    }

    @DisplayName("스트라이크 이후 던지기 테스트")
    @Test
    void testRollAfterStrike() {

        assertThatThrownBy(() -> {
            Pins pins = new Pins();
            Frame frame = NormalFrame.generateNormalFrame();
            // when
            pins.addPins(frame, 10);
            pins.addPins(frame, 5);

        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스페어 상태인지 테스트")
    @Test
    void testSpare() {
        Pins points = new Pins(Arrays.asList(new Pin(5), new Pin(5)));
        assertThat(
                points.getScoreType()
        ).isEqualTo(ScoreType.SPARE);
    }

    @DisplayName("스트라이크 상태인지 테스트")
    @Test
    void testStrike() {
        Pins points = new Pins(Arrays.asList(new Pin(10)));
        assertThat(points.getScoreType()).isEqualTo(ScoreType.STRIKE);
    }

    @DisplayName("미스 상태인지 테스트")
    @Test
    void testMiss() {
        Pins points = new Pins(Arrays.asList(new Pin(3), new Pin(0)));
        assertThat(points.getScoreType()).isEqualTo(ScoreType.MISS);
    }

}

