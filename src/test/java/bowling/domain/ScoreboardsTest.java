package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.score.Score;
import bowling.domain.score.TotalScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoreboardsTest {

    private Name name;
    private Round round;
    private Scoreboard scoreboard;

    @BeforeEach
    void setUp() {
        this.name = new Name("cys");
        this.round = new Round(1);
        this.scoreboard = new Scoreboard(this.name);
    }

    @Test
    void isEndTurnTrue() {
        this.scoreboard.addScore(Score.of(7), this.round);
        this.scoreboard.addScore(Score.of(2), this.round);
        Scoreboards scoreboards = new Scoreboards();
        scoreboards.add(this.scoreboard);

        assertThat(scoreboards.isEndTurn(new Round(1), 0)).isTrue();
    }

    @Test
    void isEndTurnStrikeTrue() {
        this.scoreboard.addScore(Score.of(10), this.round);
        Scoreboards scoreboards = new Scoreboards();
        scoreboards.add(this.scoreboard);

        assertThat(scoreboards.isEndTurn(new Round(1), 0)).isTrue();
    }

    @Test
    void isNotEndFalse() {
        this.scoreboard.addScore(Score.of(8), this.round);
        Scoreboards scoreboards = new Scoreboards();
        scoreboards.add(this.scoreboard);

        assertThat(scoreboards.isEndTurn(new Round(1), 0)).isFalse();
    }

    @Test
    void addScore() {
        this.scoreboard.addScore(Score.of(8), this.round);
        Scoreboards scoreboards = new Scoreboards();
        scoreboards.add(this.scoreboard);
        TotalScore totalScore = TotalScore.defaultFrameTotalScore();
        totalScore.addRegularScore(Score.of(8));

        assertThat(scoreboards.scoreboards().get(0).frame(this.round).totalScore()).isEqualTo(totalScore);
    }

    @Test
    void scoreboards() {
        Scoreboards scoreboards = new Scoreboards();
        Scoreboard scoreboard1 = new Scoreboard(new Name("cys"));
        scoreboards.add(scoreboard1);
        Scoreboard scoreboard2 = new Scoreboard(new Name("abc"));
        scoreboards.add(scoreboard2);

        assertThat(scoreboards.scoreboards()).containsExactly(scoreboard1, scoreboard2);
    }
}
