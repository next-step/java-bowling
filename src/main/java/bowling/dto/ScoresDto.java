package bowling.dto;

import bowling.domain.engine.frame.Score;
import bowling.domain.engine.frame.UnavailableScore;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class ScoresDto {

    private final List<ScoreDto> scores;

    private ScoresDto(List<ScoreDto> scores) {
        this.scores = scores;
    }

    public static ScoresDto of(List<Score> scores) {
        Score[] scoreArray = scores.toArray(new Score[0]);

        Arrays.parallelPrefix(scoreArray, ScoresDto::tryToAddTwoScore);

        return Arrays.stream(scoreArray)
                     .map(ScoreDto::of)
                     .collect(collectingAndThen(toList(), ScoresDto::new));
    }

    private static Score tryToAddTwoScore(Score score1, Score score2) {
        if (checkOneOfTheScoreIsUnavailable(score1, score2)) {
            return UnavailableScore.init();
        }

        return Score.initReadyToUseScore(score1.getValue() + score2.getValue());
    }

    private static boolean checkOneOfTheScoreIsUnavailable(Score score1, Score score2) {
        return score1.isUnavailable() || score2.isUnavailable();
    }

    public List<ScoreDto> getScores() {
        return scores;
    }
}
