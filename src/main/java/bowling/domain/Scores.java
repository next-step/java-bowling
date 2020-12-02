package bowling.domain;

import bowling.domain.scores.ScoresContext;
import bowling.dto.ScoresDto;

import java.util.List;

class Scores {
    private final ScoresContext context = new ScoresContext();

    int size() {
        return context.size();
    }

    void accumulate(List<Integer> scoreList) {
        scoreList.stream()
                .map(Score::new)
                .forEach(this::accumulate);
    }

    private void accumulate(Score score) {
        context.accumulate(score);
    }

    ScoresDto exportScoresDto() {
        return context.exportScoresDto();
    }
}
