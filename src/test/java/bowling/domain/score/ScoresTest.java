package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.score.scores.DefaultFrameScores;
import bowling.domain.score.scores.LastFrameScores;
import bowling.domain.score.scores.Scores;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScoresTest {

    @Test
    void create() {
        Scores scores = new DefaultFrameScores();

        assertThat(scores).isEqualTo(new DefaultFrameScores());
    }

    @Test
    void add() {
        Scores scores = new DefaultFrameScores();
        scores.add(Score.of(4));

        assertThat(scores.first()).isEqualTo(Score.of(4));
    }
    
    @Test
    void first() {
        Scores scores = new DefaultFrameScores();
        scores.add(Score.of(1));

        assertThat(scores.first()).isEqualTo(Score.of(1));
    }

    @Test
    void second() {
        Scores scores = new DefaultFrameScores();
        scores.add(Score.of(1));
        scores.add(Score.of(2));

        assertThat(scores.second()).isEqualTo(Score.of(2));
    }

    @Test
    void third() {
        Scores scores = new LastFrameScores();
        scores.add(Score.of(1));
        scores.add(Score.of(2));
        scores.add(Score.of(3));

        assertThat(scores.third()).isEqualTo(Score.of(3));
    }

    @Test
    void sum() {
        Scores scores = new LastFrameScores();
        scores.add(Score.of(1));
        scores.add(Score.of(2));
        scores.add(Score.of(3));

        assertThat(scores.sum()).isEqualTo(6);
    }

    @Test
    void sum_scores() {
        assertThat(Scores.sumScores(Score.of(1), Score.of(2), Score.of(3))).isEqualTo(6);
    }

    @Test
    void is_not_end_score() {
        TotalScore endScore = TotalScore.lastFrameTotalScore();
        endScore.addRegularScore(Score.of(10));
        endScore.addRegularScore(Score.of(10));
        endScore.addRegularScore(Score.of(10));

        TotalScore notEndScore = TotalScore.lastFrameTotalScore();
        notEndScore.addRegularScore(Score.of(2));

        Assertions.assertAll(
                () -> assertThat(endScore.regularScores().isNotEndScore(endScore.regularScores())).isFalse(),
                () -> assertThat(notEndScore.regularScores().isNotEndScore(notEndScore.regularScores())).isTrue()
        );
    }

}
