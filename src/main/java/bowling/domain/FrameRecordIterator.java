package bowling.domain;

import java.util.Iterator;

public class FrameRecordIterator {

    private final Player player;
    private final Iterator<String> markingIterator;

    public FrameRecordIterator(Player player, Iterator<String> markingIterator) {
        this.player = player;
        this.markingIterator = markingIterator;
    }

    public Player getPlayer() {
        return player;
    }

    public Iterator<String> getMarkingIterator() {
        return markingIterator;
    }
}
