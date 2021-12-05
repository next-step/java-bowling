package bowling.domain.value;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("프레임별 모든 볼링핀 테스트")
class FramePinsTest {
    private FramePins framePins;

    @BeforeEach
    void setup() {
        framePins = FramePins.create();

        framePins.addPins(Pins.from(10));
        framePins.addPins(Pins.from(4));
    }

    @Test
    @DisplayName("정상적으로 핀 생성")
    void create() {
        assertDoesNotThrow(FramePins::create);
    }

    @Test
    @DisplayName("핀 총함 검증")
    void calculateTotalPins() {
        assertThat(framePins.calculateTotalPins()).isEqualTo(14);
    }

    @Test
    @DisplayName("첫번째 핀이 스트라이크 인지 검증")
    void isFirstPitchStrike() {
        assertThat(framePins.isFirstPitchStrike()).isTrue();
    }
}
