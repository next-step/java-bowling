package bowling.domain;

import java.util.Iterator;

public class FrameRecordIterator {

    private final Player player;
    private final Iterator<String> markingIterator;
    private final Iterator<Integer> scoreIterator;

    public FrameRecordIterator(Player player,
                               Iterator<String> markingIterator,
                               Iterator<Integer> scoreIterator) {
        this.player = player;
        this.markingIterator = markingIterator;
        this.scoreIterator = scoreIterator;
    }

    public Player getPlayer() {
        return player;
    }

    public Iterator<String> getMarkingIterator() {
        return markingIterator;
    }

    public Iterator<Integer> getScoreIterator() {
        return scoreIterator;
    }
}
