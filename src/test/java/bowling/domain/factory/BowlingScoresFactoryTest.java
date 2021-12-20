package bowling.domain.factory;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.scores.FinalScores;
import bowling.domain.scores.GeneralScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingScoresFactoryTest {

    private final BowlingScoresFactory bowlingScoresFactory = new BowlingScoresFactory();

    @Test
    @DisplayName("1-9 라운드는 GeneralScores 가 생성된다.")
    void createTest() {

        for (int round = 0; round < 8; round++) {
            assertThat(bowlingScoresFactory.create(round)).isInstanceOf(GeneralScores.class);
        }
    }

    @Test
    @DisplayName("10 라운드는 FinalScores 가 생성된다.")
    void createFinalScoresTest() {
        assertThat(bowlingScoresFactory.create(9)).isInstanceOf(FinalScores.class);
    }
}