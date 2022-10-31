package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DefaultCalculatorTest {

    @Test
    void should(){
        ScoreCalculator calculator = new DefaultCalculator();

        BowlingRound bowlingRound = new BowlingRound(1);
        bowlingRound.addKnockDownPins(3);
        bowlingRound.addKnockDownPins(5);

        int score = calculator.calculate(List.of(bowlingRound));

        assertThat(score).isEqualTo(8);
    }
}
