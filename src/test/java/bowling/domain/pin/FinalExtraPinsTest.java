package bowling.domain.pin;

import bowling.domain.Score;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class FinalExtraPinsTest {

    @Test
    void addSpareExtra_Exception() {
        FinalExtraPins extraPins = new FinalExtraPins();

        assertThatThrownBy(() -> {
            extraPins.addSpareExtra(Pin.of(4));
            extraPins.addSpareExtra(Pin.of(4));
        }).isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @CsvSource({"8,4", "9,2"})
    void addStrikeExtra_Exception(int firstPin, int secondPin) {
        FinalExtraPins extraPins = new FinalExtraPins();

        assertThatThrownBy(() -> {
            extraPins.addStrikeExtra(Pin.of(firstPin));
            extraPins.addStrikeExtra(Pin.of(secondPin));
        }).isInstanceOf(IllegalStateException.class);
    }

    @ParameterizedTest
    @CsvSource({"10,10", "7,3", "7,2"})
    void addStrikeExtra(int firstPin, int secondPin) {
        FinalExtraPins extraPins = new FinalExtraPins();

        assertThatNoException().isThrownBy(() -> {
            extraPins.addStrikeExtra(Pin.of(firstPin));
            extraPins.addStrikeExtra(Pin.of(secondPin));
        });
    }

    @Test
    void spareScore() {
        FinalExtraPins extraPins = new FinalExtraPins();
        extraPins.addSpareExtra(Pin.of(4));

        Score score = extraPins.spareScore(Score.ofSpare());

        assertThat(score.getScore()).isEqualTo(14);
    }

    @Test
    void strikeScore() {
        FinalExtraPins extraPins = new FinalExtraPins();
        extraPins.addStrikeExtra(Pin.of(4));
        extraPins.addStrikeExtra(Pin.of(5));

        Score score = extraPins.strikeScore(Score.ofStrike());

        assertThat(score.getScore()).isEqualTo(19);
    }

    @Test
    void expression_Miss() {
        FinalExtraPins extraPins = new FinalExtraPins();

        String expression = extraPins.expression(new Miss(5, 4));

        assertThat(expression).isEqualTo("5|4");
    }

    @ParameterizedTest
    @CsvSource({"5,5,10,5|/|X",
            "6,4,5,6|/|5"})
    void expression_Spare(int firstPin, int secondPin, int spareExtra, String result) {
        FinalExtraPins extraPins = new FinalExtraPins();
        extraPins.addSpareExtra(Pin.of(spareExtra));

        String expression = extraPins.expression(new Spare(firstPin, secondPin));

        assertThat(expression).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource({"8,1,X|8|1",
            "8,2,X|8|/",
            "10,5,X|X|5",
            "10,10,X|X|X"})
    void expression_Strike(int firstStrikeExtra, int secondStrikeExtra, String result) {
        FinalExtraPins extraPins = new FinalExtraPins();
        extraPins.addStrikeExtra(Pin.of(firstStrikeExtra));
        extraPins.addStrikeExtra(Pin.of(secondStrikeExtra));

        String expression = extraPins.expression(new Strike());

        assertThat(expression).isEqualTo(result);
    }
}
