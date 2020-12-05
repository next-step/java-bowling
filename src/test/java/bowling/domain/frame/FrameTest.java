package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Pins;
import bowling.exception.BadCountOfPinsException;
import bowling.exception.PinsOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FrameTest {
    private Pins pins;

    @BeforeEach
    void setUp() {
        pins = new Pins();
    }

    @Test
    @DisplayName("한 프레임에 쓰러트린 핀 갯수가 10개를 넘으면 BadCountOfPinsException 이 발생한다.")
    void badCountOfPins() {
        pins.add(Pin.of(9));
        Frame frame = Frame.of(pins);
        pins.add(Pin.of(9));
        assertThatExceptionOfType(BadCountOfPinsException.class)
                .isThrownBy(() -> frame.update(pins))
                .withMessage("한 프레임에서 쓰러트린 핀의 개수는 0 이상 10 이하여야 합니다.");
    }

    @Test
    @DisplayName("프레임이 UNFINISHED")
    void unfinished() {
        pins.add(Pin.of(9));
        Frame frame = Frame.of(pins);
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.UNFINISHED),
                () -> assertThat(frame.hasScore(pins))
                        .isFalse(),
                () -> assertThat(frame.getScore(pins))
                        .isEqualTo(-1)
        );
    }

    @Test
    @DisplayName("프레임이 STRIKE")
    void strike() {
        pins.add(Pin.of(10));
        Frame frame = Frame.of(pins);
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.STRIKE),
                () -> assertThat(frame.hasScore(pins))
                        .isFalse(),
                () -> assertThatExceptionOfType(PinsOutOfRangeException.class)
                        .isThrownBy(() -> frame.getScore(pins))
                        .withMessage("pins 의 범위를 벗어난 index 입니다."),
                () -> assertDoesNotThrow(() -> {
                    pins.add(Pin.of(10));
                    pins.add(Pin.of(5));
                    pins.add(Pin.of(3));
                    frame.update(pins);
                }),
                () -> assertThat(frame.getScore(pins))
                        .isEqualTo(25)
        );
    }

    @Test
    @DisplayName("프레임이 SPARE")
    void spare() {
        pins.add(Pin.of(0));
        Frame frame = Frame.of(pins);
        pins.add(Pin.of(10));
        frame.update(pins);
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.SPARE),
                () -> assertThat(frame.hasScore(pins))
                        .isFalse(),
                () -> assertThatExceptionOfType(PinsOutOfRangeException.class)
                        .isThrownBy(() -> frame.getScore(pins))
                        .withMessage("pins 의 범위를 벗어난 index 입니다."),
                () -> assertDoesNotThrow(() -> {
                    pins.add(Pin.of(10));
                    pins.add(Pin.of(5));
                    pins.add(Pin.of(3));
                    frame.update(pins);
                }),
                () -> assertThat(frame.getScore(pins))
                        .isEqualTo(20)
        );
    }

    @Test
    @DisplayName("프레임이 MISS")
    void miss() {
        pins.add(Pin.of(0));
        Frame frame = Frame.of(pins);
        pins.add(Pin.of(9));
        frame.update(pins);
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.MISS),
                () -> assertThat(frame.hasScore(pins))
                        .isTrue(),
                () -> assertThat(frame.getScore(pins))
                        .isEqualTo(9),
                () -> assertDoesNotThrow(() -> {
                    pins.add(Pin.of(10));
                    pins.add(Pin.of(5));
                    pins.add(Pin.of(3));
                    frame.update(pins);
                }),
                () -> assertThat(frame.getScore(pins))
                        .isEqualTo(9)
        );
    }
}
