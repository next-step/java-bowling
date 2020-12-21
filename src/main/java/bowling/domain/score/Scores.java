package bowling.domain.score;

import bowling.domain.frame.FrameNumber;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Scores {

    private List<Score> scores;

    private Scores() {
        this.scores = IntStream.rangeClosed(0, 9)
            .mapToObj(i -> Score.create(0, ScoreType.READY))
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public static Scores create() {
        return new Scores();
    }

    public void updateScore(FrameNumber frameNumber, Score score) {
        scores.set(frameNumber.getValue(), score);
    }

    public Score getFromIndex(FrameNumber frameNumber) {
        return scores.get(frameNumber.getValue());
    }

    public List<Integer> getDownPinsFromIndex(FrameNumber frameNumber) {
        Optional<List<Integer>> downPins = Optional.of(scores.get(frameNumber.getValue()).getDownPins());
        return downPins.orElse(new ArrayList<>());
    }
}
