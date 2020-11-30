package bowling.domain;

import bowling.dto.ScoresDto;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

class Scores {
    private final List<Score> scores = new LinkedList<>();

    int size() {
        return scores.size();
    }

    void addValidOnly(List<Score> scoreList) {
        scores.addAll(scoreList.stream()
                .filter(Score::isValid)
                .collect(toList())
        );
    }

    ScoresDto exportScoresDto() {
        return scores.stream()
                .map(Score::exportScoreDto)
                .collect(collectingAndThen(toList(), ScoresDto::new));
    }
}
