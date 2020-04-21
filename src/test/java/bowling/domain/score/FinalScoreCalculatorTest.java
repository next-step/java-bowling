package bowling.domain.score;

import bowling.domain.pitch.Pitch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalScoreCalculatorTest {
    private FinalScoreCalculator finalScore;

    @BeforeEach
    void setUp() {
        finalScore = new FinalScoreCalculator();
    }

    @DisplayName("두번의 투구 점수를 합산한다.")
    @Test
    void sumScoreForTwo() {
        int pinCount = 1;
        int result = 2;

        finalScore.add(new Pitch(pinCount));
        finalScore.add(new Pitch(pinCount));

        assertThat(finalScore.calculateScore().getScore()).isEqualTo(result);
    }

    @DisplayName("스페어일 경우 세번의 투구 점수를 합산한다.")
    @Test
    void sumScoreForSpare() {
        int pinCount = 1;
        int sparePinCount = 9;
        int result = 11;

        Pitch pitch = new Pitch(pinCount);
        finalScore.add(pitch);
        finalScore.add(pitch.next(sparePinCount));
        finalScore.add(new Pitch(pinCount));

        assertThat(finalScore.calculateScore().getScore()).isEqualTo(result);
    }

    @DisplayName("스트라이크일 경우 세번의 투구 점수를 합산한다.")
    @Test
    void sumScoreForStrike() {
        int strikePinCount = 10;
        int pinCount = 1;
        int result = 12;

        finalScore.add(new Pitch(strikePinCount));
        finalScore.add(new Pitch(pinCount));
        finalScore.add(new Pitch(pinCount));

        assertThat(finalScore.calculateScore().getScore()).isEqualTo(result);
    }
}
