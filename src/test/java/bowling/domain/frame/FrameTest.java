package bowling.domain.frame;

import bowling.exception.BadCountOfDownPinsException;
import bowling.exception.PinsOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FrameTest {
    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new Frame();
    }

    @Test
    @DisplayName("한 프레임에 쓰러트린 핀 갯수가 10개를 넘으면 BadCountOfDownPinsException 이 발생한다.")
    void badCountOfDownPins() {
        assertThatExceptionOfType(BadCountOfDownPinsException.class)
                .isThrownBy(() -> {
                    frame.addPin(Pin.of(9));
                    frame.addPin(Pin.of(9));
                })
                .withMessage("한 프레임에서 쓰러트린 핀의 개수는 0 이상 10 이하여야 합니다.");
    }

    @Test
    @DisplayName("프레임이 UNFINISHED")
    void unfinished() {
        frame.addPin(Pin.of(9));
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.UNFINISHED),
                () -> assertThat(frame.hasScore())
                        .isFalse(),
                () -> assertThatExceptionOfType(PinsOutOfRangeException.class)
                        .isThrownBy(() -> frame.getCountOfDownPins())
                        .withMessage("pins 의 범위를 벗어난 index 입니다.")
        );
    }

    @Test
    @DisplayName("프레임이 STRIKE")
    void strike() {
        frame.addPin(Pin.of(10));
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.STRIKE),
                () -> assertThat(frame.hasScore())
                        .isFalse(),
                () -> assertThatExceptionOfType(PinsOutOfRangeException.class)
                        .isThrownBy(() -> frame.getCountOfDownPins())
                        .withMessage("pins 의 범위를 벗어난 index 입니다."),

                () -> assertDoesNotThrow(() -> {
                    frame.addPin(Pin.of(10));
                    frame.addPin(Pin.of(5));
                    frame.addPin(Pin.of(3));
                }),

                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.STRIKE),
                () -> assertThat(frame.hasScore())
                        .isTrue(),
                () -> assertThat(frame.getCountOfDownPins())
                        .isEqualTo(25)
        );
    }

    @Test
    @DisplayName("프레임이 SPARE")
    void spare() {
        frame.addPin(Pin.of(0));
        frame.addPin(Pin.of(10));
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.SPARE),
                () -> assertThat(frame.hasScore())
                        .isFalse(),
                () -> assertThatExceptionOfType(PinsOutOfRangeException.class)
                        .isThrownBy(() -> frame.getCountOfDownPins())
                        .withMessage("pins 의 범위를 벗어난 index 입니다."),

                () -> assertDoesNotThrow(() -> {
                    frame.addPin(Pin.of(10));
                    frame.addPin(Pin.of(5));
                    frame.addPin(Pin.of(3));
                }),

                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.SPARE),
                () -> assertThat(frame.hasScore())
                        .isTrue(),
                () -> assertThat(frame.getCountOfDownPins())
                        .isEqualTo(20)
        );
    }

    @Test
    @DisplayName("프레임이 MISS")
    void miss() {
        frame.addPin(Pin.of(0));
        frame.addPin(Pin.of(9));
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.MISS),
                () -> assertThat(frame.hasScore())
                        .isTrue(),
                () -> assertThat(frame.getCountOfDownPins())
                        .isEqualTo(9),

                () -> assertDoesNotThrow(() -> {
                    frame.addPin(Pin.of(10));
                    frame.addPin(Pin.of(5));
                    frame.addPin(Pin.of(3));
                }),

                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.MISS),
                () -> assertThat(frame.hasScore())
                        .isTrue(),
                () -> assertThat(frame.getCountOfDownPins())
                        .isEqualTo(9)
        );
    }
}
