package bowling.domain.bowl;

import bowling.dto.ScoresDto;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

class Scores {
    private final List<Score> scores = new LinkedList<>();
    private int accumulated = 0;

    void accumulate(int countOfDownPins) {
        accumulated += countOfDownPins;
        scores.add(new Score(accumulated));
    }

    ScoresDto exportScoresDto() {
        return scores
                .stream()
                .map(Score::exportScoreDto)
                .collect(collectingAndThen(toList(), ScoresDto::new));
    }
}
