package bowling.domain;

import bowling.exception.BadCountOfPinsException;
import bowling.exception.RollsOutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FrameTest {

    private Rolls rolls;

    @BeforeEach
    void setUp() {
        rolls = new Rolls();
    }

    @Test
    @DisplayName("프레임이 UNFINISHED")
    void unfinished() {
        rolls.add(Roll.of(9));
        Frame frame = Frame.of(rolls);
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.UNFINISHED),
                () -> assertThat(frame.hasScore(rolls))
                        .isFalse(),
                () -> assertThat(frame.getScore(rolls))
                        .isEqualTo(-1)
        );
    }

    @Test
    @DisplayName("한 프레임에 쓰러트린 핀 갯수가 10개를 넘으면 BadCountOfPinsException 이 발생한다.")
    void badCoutOfPins() {
        rolls.add(Roll.of(9));
        Frame frame = Frame.of(rolls);
        rolls.add(Roll.of(9));
        assertThatExceptionOfType(BadCountOfPinsException.class)
                .isThrownBy(() -> frame.update(rolls))
                .withMessage("한 프레임에서 쓰러트린 핀의 갯수는 0이상 10이하 여야 합니다.");
    }

    @Test
    @DisplayName("프레임이 STRIKE")
    void strike() {
        rolls.add(Roll.of(10));
        Frame frame = Frame.of(rolls);
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.STRIKE),
                () -> assertThat(frame.hasScore(rolls))
                        .isFalse(),
                () -> assertThatExceptionOfType(RollsOutOfRangeException.class)
                        .isThrownBy(() -> frame.getScore(rolls))
                        .withMessage("rolls 의 범위를 벗어난 index 입니다."),
                () -> assertDoesNotThrow(() -> {
                    rolls.add(Roll.of(10));
                    rolls.add(Roll.of(5));
                    rolls.add(Roll.of(3));
                    frame.update(rolls);
                }),
                () -> assertThat(frame.getScore(rolls))
                        .isEqualTo(25)
        );
    }

    @Test
    @DisplayName("프레임이 SPARE")
    void spare() {
        rolls.add(Roll.of(0));
        Frame frame = Frame.of(rolls);
        rolls.add(Roll.of(10));
        frame.update(rolls);
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.SPARE),
                () -> assertThat(frame.hasScore(rolls))
                        .isFalse(),
                () -> assertThatExceptionOfType(RollsOutOfRangeException.class)
                        .isThrownBy(() -> frame.getScore(rolls))
                        .withMessage("rolls 의 범위를 벗어난 index 입니다."),
                () -> assertDoesNotThrow(() -> {
                    rolls.add(Roll.of(10));
                    rolls.add(Roll.of(5));
                    rolls.add(Roll.of(3));
                    frame.update(rolls);
                }),
                () -> assertThat(frame.getScore(rolls))
                        .isEqualTo(20)
        );
    }

    @Test
    @DisplayName("프레임이 MISS")
    void miss() {
        rolls.add(Roll.of(0));
        Frame frame = Frame.of(rolls);
        rolls.add(Roll.of(9));
        frame.update(rolls);
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.MISS),
                () -> assertThat(frame.hasScore(rolls))
                        .isTrue(),
                () -> assertThat(frame.getScore(rolls))
                        .isEqualTo(9),
                () -> assertDoesNotThrow(() -> {
                    rolls.add(Roll.of(10));
                    rolls.add(Roll.of(5));
                    rolls.add(Roll.of(3));
                    frame.update(rolls);
                }),
                () -> assertThat(frame.getScore(rolls))
                        .isEqualTo(9)
        );
    }

}
