package bowling.score;

import bowling.frame.LastFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static bowling.frame.ConstShootScore.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScoreBoardTest {

    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        scoreBoard = ScoreBoard.create();
    }

    @Test
    void lastFrameScoreBoard() {
        LastFrame tripleLastFrame = LastFrame.create();

        tripleLastFrame.shoot(STRIKE);
        tripleLastFrame.shoot(STRIKE);
        tripleLastFrame.shoot(STRIKE);

        scoreBoard.lastBonusScore(tripleLastFrame);
        assertThat(scoreBoard.scores().get(0))
                .isEqualTo(30);
    }

    @Test
    void spareAndLastShootBoard() {
        LastFrame spareAndLastShoot = LastFrame.create();

        spareAndLastShoot.shoot(FIVE_SCORE);
        spareAndLastShoot.shoot(FIVE_SCORE);
        spareAndLastShoot.shoot(FIVE_SCORE);

        scoreBoard.lastBonusScore(spareAndLastShoot);
        assertThat(scoreBoard.scores().get(0))
                .isEqualTo(15);
    }

    @Test
    void missLastFrameBoard() {
        LastFrame missLastFrame = LastFrame.create();

        missLastFrame.shoot(FOUR_SCORE);
        missLastFrame.shoot(FIVE_SCORE);

        scoreBoard.lastBonusScore(missLastFrame);
        assertThat(scoreBoard.scores().get(0))
                .isEqualTo(9);
    }

}