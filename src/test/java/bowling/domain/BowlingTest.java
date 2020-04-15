package bowling.domain;

import bowling.exception.BowlingException;
import bowling.exception.ExceptionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BowlingTest {
    @Test
    void bowling() {
        String name = "sseul";

        assertThatThrownBy(() -> new Bowling(name))
                .isInstanceOf(BowlingException.class)
                .hasMessageContaining(ExceptionType.INVALID_NAME_LENGTH.getErrorMessage());
    }

    @Test
    void play() {
        Bowling bowling = new Bowling("PES");

        bowling.play(8);
        bowling.play(1);

        assertThat(bowling.getFrames().getFrames()).hasSize(1);
        assertThat(bowling.getFrames().getFrames().get(0).getFrameRounds().getScoreStatus().getStatus()).isEqualTo(RoundsStatus.MISS);
    }

    @Test
    void getLastFrameNumber() {
        Bowling bowling = new Bowling("PES");
        bowling.play(8);

        assertThat(bowling.getLastFrameNumber()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:true", "9:false"}, delimiter = ':')
    void isEnd(int value, boolean expected) {
        Bowling bowling = new Bowling("PES");

        for (int i = 0; i < value; i++) {
            bowling.play(8);
            bowling.play(1);
        }

        assertThat(bowling.isEnd()).isEqualTo(expected);
    }
}
