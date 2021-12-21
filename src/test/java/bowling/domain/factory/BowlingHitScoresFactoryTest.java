package bowling.domain.factory;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.scores.FinalHitScores;
import bowling.domain.scores.GeneralHitScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingHitScoresFactoryTest {

    private final BowlingHitScoresFactory bowlingScoresFactory = new BowlingHitScoresFactory();

    @Test
    @DisplayName("1-9 라운드는 GeneralHitScores 가 생성된다.")
    void createTest() {

        for (int round = 0; round < 8; round++) {
            assertThat(bowlingScoresFactory.create(round)).isInstanceOf(GeneralHitScores.class);
        }
    }

    @Test
    @DisplayName("10 라운드는 FinalHitScores 가 생성된다.")
    void createFinalScoresTest() {
        assertThat(bowlingScoresFactory.create(9)).isInstanceOf(FinalHitScores.class);
    }
}