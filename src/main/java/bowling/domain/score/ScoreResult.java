package bowling.domain.score;

import bowling.view.ResultView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By mand2 on 2020-12-21.
 */
public class ScoreResult {

    private final List<BowlingScore> bowlingScores;

    private ScoreResult(List<BowlingScore> bowlingScores) {
        this.bowlingScores = bowlingScores;
    }

    public static ScoreResult of(List<BowlingScore> bowlingScores) {
        return new ScoreResult(bowlingScores);
    }

    public String printScore() {
        return this.bowlingScores.stream()
                .map(BowlingScore::getName)
                .collect(Collectors.joining(ResultView.DELIMITER));
    }


}
