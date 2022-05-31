package bowling.domain;

import bowling.frame.Frames;
import bowling.score.Scores;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.frame.ConstShootScore.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = BowlingGame.from(PlayerName.from("LDH"), Frames.create(), Scores.create());
    }

    @Test
    @DisplayName("스트라이크를 쳤을 때 다음 라운드로 이동해야함")
    void shootStrike() {
        assertAll(
                () -> assertThat(bowlingGame.currentRound()).isEqualTo(0),
                () -> bowlingGame.shoot(STRIKE),
                () -> assertThat(bowlingGame.currentRound()).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("같은 프레임에서 10점을 초과하는 점수를 입력했을 경우 예외가 발생함")
    void invalidShootScore() {
        bowlingGame.shoot(FIVE_SCORE);
        assertThatThrownBy(() -> bowlingGame.shoot(SEVEN_SCORE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("볼링 한 게임 작동(10 프레임)")
    void oneGame() {
        bowlingGame.shoot(SIX_SCORE);
        bowlingGame.shoot(FOUR_SCORE);

        bowlingGame.shoot(STRIKE);

        bowlingGame.shoot(STRIKE);

        bowlingGame.shoot(THREE_SCORE);
        bowlingGame.shoot(FIVE_SCORE);

        bowlingGame.shoot(SIX_SCORE);
        bowlingGame.shoot(FOUR_SCORE);

        bowlingGame.shoot(STRIKE);

        bowlingGame.shoot(STRIKE);

        bowlingGame.shoot(EIGHT_SCORE);
        bowlingGame.shoot(ONE_SCORE);

        bowlingGame.shoot(NINE_SCORE);
        bowlingGame.shoot(ZERO_SCORE);

        bowlingGame.shoot(STRIKE);
        bowlingGame.shoot(EIGHT_SCORE);
        bowlingGame.shoot(TWO_SCORE);

        System.out.println(bowlingGame.totalScores());
    }
}