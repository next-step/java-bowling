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

        framePins.addPins(Pins.from(4));
        framePins.addPins(Pins.from(10));
    }

    @Test
    @DisplayName("정상적으로 핀 생성")
    void create() {
        assertDoesNotThrow(FramePins::create);
    }

    @Test
    @DisplayName("투구 숫자 검증")
    void getCountOfPitch() {
        assertThat(framePins.isFrameOver(2)).isTrue();
    }

    @Test
    @DisplayName("핀 총함 검증")
    void getTotalPins() {
        assertThat(framePins.getTotalPins()).isEqualTo(14);
    }
}
