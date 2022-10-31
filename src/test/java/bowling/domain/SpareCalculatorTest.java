package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SpareCalculatorTest {

    @Test
    void should(){
        ScoreCalculator calculator = new SpareCalculator();
        BowlingRound bowlingRoundA = new BowlingRound(1);
        BowlingRound bowlingRoundB = new BowlingRound(2);

        bowlingRoundA.addKnockDownPins(1);
        bowlingRoundA.addKnockDownPins(9);
        bowlingRoundB.addKnockDownPins(5);
        int sum = calculator.calculate(List.of(bowlingRoundA, bowlingRoundB));

        assertThat(sum).isEqualTo(15);
    }

}
