package bowling.domain.scores;

import bowling.dto.ScoresDto;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Scores {
    private final LinkedList<Score> scores = new LinkedList<>();
    private ScoresState state = EmptyScoresState.getInstance();

    void setState(ScoresState state) {
        this.state = state;
    }

    void addScore(Score score) {
        scores.add(score);
    }

    Score getLast() {
        return scores.getLast();
    }

    public int size() {
        return scores.size();
    }

    public void accumulate(List<Integer> scoreList) {
        scoreList.stream()
                .map(Score::new)
                .forEach(this::accumulate);
    }

    private void accumulate(Score score) {
        state.accumulate(this, score);
    }

    public ScoresDto exportScoresDto() {
        return scores.stream()
                .map(Score::exportScoreDto)
                .collect(collectingAndThen(toList(), ScoresDto::new));
    }
}
