package bowling.dto;

import bowling.domain.engine.frame.Score;

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

        Arrays.parallelPrefix(scoreArray, (score1, score2) ->
            Score.initReadyToUseScore(score1.getValue() + score2.getValue()));

        return Arrays.stream(scoreArray)
                     .map(ScoreDto::of)
                     .collect(collectingAndThen(toList(), ScoresDto::new));
    }

    public List<ScoreDto> getScores() {
        return scores;
    }
}
