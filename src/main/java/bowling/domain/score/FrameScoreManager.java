package bowling.domain.score;

import bowling.domain.roll.Roll;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FrameScoreManager {

    private final List<FrameScore> frameScores = new ArrayList<>();
    private final ScoreManager scoreManager = new ScoreManager();

    public void notify(Roll roll) {
        scoreManager.notifyObservers(roll);
    }

    public void registerEndFrame(FrameScore frameScore) {
        frameScores.add(frameScore);
        if (frameScore.addable()) {
            scoreManager.registerObserver(frameScore);
        }
    }

    public int size() {
        return scoreManager.observerSize();
    }

    public Iterator<Integer> scoreIterator() {
        AtomicInteger counter = new AtomicInteger();
        return frameScores.stream()
                .filter(frameScore -> !frameScore.addable())
                .map(frameScore -> counter.addAndGet(frameScore.getScore().getScore()))
                .iterator();
    }
}
