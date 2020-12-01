package bowling.domain;

import bowling.dto.ScoresDto;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

class Scores {
    private final LinkedList<Score> scores = new LinkedList<>();

    int size() {
        return scores.size();
    }

    void accumulateOnlyValid(List<Score> scoreList) {
        scoreList.stream()
                .filter(Score::isValid)
                .forEach(this::accumulate);
    }

    private void accumulate(Score score) {
        Score cumulated = size() < 1
                ? score
                : scores.getLast()
                .sum(score);
        scores.add(cumulated);
    }

    ScoresDto exportScoresDto() {
        return scores.stream()
                .map(Score::exportScoreDto)
                .collect(collectingAndThen(toList(), ScoresDto::new));
    }
}
