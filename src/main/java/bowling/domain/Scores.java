package bowling.domain;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

class Scores {
    private final List<Score> scores = new LinkedList<>();

    int size() {
        return scores.size();
    }

    void update(Rolls rolls, Frames frames) {
        scores.addAll(
                frames.subList(scores.size(), frames.size())
                        .stream()
                        .map(frame -> frame.score(rolls))
                        .map(Score::new)
                        .filter(Score::isValid)
                        .collect(toList())
        );
    }
}
