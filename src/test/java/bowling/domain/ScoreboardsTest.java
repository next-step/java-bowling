package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.score.Score;
import bowling.domain.score.TotalScore;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoreboardsTest {

    private Name name;
    private Round round;

    @BeforeEach
    void setUp() {
        this.name = new Name("cys");
        this.round = new Round(1);
    }

    private Names createNames(String... names) {
        return new Names(Arrays.stream(names).map(Name::new).collect(Collectors.toList()));
    }

    @Test
    void isEndTurnTrue() {
        Scoreboards scoreboards = new Scoreboards(new Names(List.of(name)));
        scoreboards.addScore(Score.of(7), name, this.round);
        scoreboards.addScore(Score.of(2), name, this.round);

        assertThat(scoreboards.isEndTurn(new Round(1), name)).isTrue();
    }

    @Test
    void isEndTurnStrikeTrue() {
        Scoreboards scoreboards = new Scoreboards(new Names(List.of(name)));
        scoreboards.addScore(Score.of(10), name, this.round);

        assertThat(scoreboards.isEndTurn(new Round(1), name)).isTrue();
    }

    @Test
    void isNotEndFalse() {
        Scoreboards scoreboards = new Scoreboards(new Names(List.of(name)));
        scoreboards.addScore(Score.of(8), name, this.round);

        assertThat(scoreboards.isEndTurn(new Round(1), name)).isFalse();
    }

    @Test
    void addScore() {
        Scoreboards scoreboards = new Scoreboards(new Names(List.of(name)));
        scoreboards.addScore(Score.of(8), name, this.round);
        TotalScore totalScore = TotalScore.defaultFrameTotalScore();
        totalScore.addRegularScore(Score.of(8));

        assertThat(scoreboards.scoreboards().get(name).frame(this.round).totalScore()).isEqualTo(totalScore);
    }

    @Test
    void addScoreFail() {
        Scoreboards scoreboards = new Scoreboards(new Names(List.of(name)));
        scoreboards.addScore(Score.of(8), name, this.round);
        scoreboards.addScore(Score.of(2), name, this.round);

        assertThatThrownBy(() -> scoreboards.addScore(Score.of(2), name, this.round)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @Test
    void validateName() {
        Scoreboards scoreboards = new Scoreboards(new Names(List.of(new Name("aka"))));

        assertThatThrownBy(() -> scoreboards.isEndTurn(new Round(1), new Name("cys")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
