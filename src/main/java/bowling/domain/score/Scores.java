package bowling.domain.score;

import bowling.domain.frame.Frames;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Scores implements Iterable<Score> {

    private final List<Score> scores;

    private Scores(List<Score> scores) {
        this.scores = scores;
    }

    public static Scores create() {
        return new Scores(new ArrayList<>());
    }

    public void scores(Frames frames) {
        frames.forEach(frame -> frame.calcScore(frames)
            .ifPresent(scores::add));
    }

    public List<Score> scores() {
        return this.scores;
    }

    @Override
    public Iterator<Score> iterator() {
        return scores.iterator();
    }

    @Override
    public void forEach(Consumer<? super Score> action) {
        this.scores.forEach(action);
    }

}
