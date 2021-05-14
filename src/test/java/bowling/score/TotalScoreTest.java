package bowling.score;

import bowling.entity.TotalScore;
import bowling.entity.score.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TotalScoreTest {

    private TotalScore totalScore;
    private List<ScoreType> scoreTypes;

    @BeforeEach
    public void setup(){
        totalScore = new TotalScore();
        scoreTypes = new ArrayList<>();
        scoreTypes.add(new Strike());
        scoreTypes.add(new Strike());
        scoreTypes.add(new Strike());
        scoreTypes.add(new Strike());
        scoreTypes.add(new Strike());
        scoreTypes.add(new Strike());
        scoreTypes.add(new Strike());
        scoreTypes.add(new Strike());
        scoreTypes.add(new Strike());
        totalScore.addScore(scoreTypes);
    }

    @Test
    @DisplayName("계산 가능한 스코어")
    public void calculateScore(){
        assertThat(totalScore.getFrameScoreResults(1) == 30).isTrue();
    }

    @Test
    @DisplayName("계산 가능한 스코어 (5프레임 까지 합)")
    public void calculateSumScore(){
        assertThat(totalScore.getFrameScoreResults(5) == 150).isTrue();
    }

    @Test
    @DisplayName("스페어 계산")
    public void calculateSpareFrameScore(){
        TotalScore spareTotalScore = new TotalScore();
        List<ScoreType> scoreTypes = new ArrayList<>();
        scoreTypes.add(new NormalScore(5));
        scoreTypes.add(new Spare(5));
        scoreTypes.add(new NormalScore(5));
        scoreTypes.add(new Spare(5));
        scoreTypes.add(new NormalScore(5));
        scoreTypes.add(new Spare(5));
        spareTotalScore.addScore(scoreTypes);
        assertThat(spareTotalScore.getFrameScoreResults(1) == 15).isTrue();
        assertThat(spareTotalScore.getFrameScoreResults(2) == 30).isTrue();
    }

    @Test
    @DisplayName("계산 불가능한 스코어")
    public void calculateImpossibleScore(){

        assertThatThrownBy(() -> totalScore.getFrameScoreResults(10))
                .isInstanceOf(CalculateImPossibleException.class).hasMessageContaining("아직 계산이 끝나지 않은 프레임 입니다.");
    }

    @Test
    @DisplayName("볼링 게임 총 합 계산")
    public void totalScore(){
        List<ScoreType> lastFrameScores = new LinkedList<>();
        lastFrameScores.add(new Strike(0));
        lastFrameScores.add(new Strike(0));
        lastFrameScores.add(new Strike(0));
        totalScore.addScore(lastFrameScores);

        assertThat(totalScore.getFrameScoreResults(10) == 300).isTrue();
    }
}
