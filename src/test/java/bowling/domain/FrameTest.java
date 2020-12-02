package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
                () -> assertThat(frame.score(rolls))
                        .isEqualTo(-1)
        );
    }

    @Test
    @DisplayName("프레임이 STRIKE")
    void strike() {
        rolls.add(Roll.of(10));
        Frame frame = Frame.of(rolls);
        assertAll(
                () -> assertThat(frame.exportFrameDto().getFrameEnum())
                        .isEqualTo(FrameEnum.STRIKE),
                () -> assertThat(frame.score(rolls))
                        .isEqualTo(-1),
                () -> assertDoesNotThrow(() -> {
                    rolls.add(Roll.of(10));
                    rolls.add(Roll.of(5));
                    rolls.add(Roll.of(3));
                    frame.update(rolls);
                }),
                () -> assertThat(frame.score(rolls))
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
                () -> assertThat(frame.score(rolls))
                        .isEqualTo(-1),
                () -> assertDoesNotThrow(() -> {
                    rolls.add(Roll.of(10));
                    rolls.add(Roll.of(5));
                    rolls.add(Roll.of(3));
                    frame.update(rolls);
                }),
                () -> assertThat(frame.score(rolls))
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
                () -> assertThat(frame.score(rolls))
                        .isEqualTo(9),
                () -> assertDoesNotThrow(() -> {
                    rolls.add(Roll.of(10));
                    rolls.add(Roll.of(5));
                    rolls.add(Roll.of(3));
                    frame.update(rolls);
                }),
                () -> assertThat(frame.score(rolls))
                        .isEqualTo(9)
        );
    }

}
