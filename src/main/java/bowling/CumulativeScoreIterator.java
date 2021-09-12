package bowling;

import java.util.Iterator;

public class CumulativeScoreIterator implements Iterator<ScoreFrame> {
    private static final ScoreFrames scoreFrames;
    private int index = 0;

    public CumulativeScoreIterator(ScoreFrames scoreFrames) {
            this.scoreFrames = scoreFrames;
    }

    @Override
    public boolean hasNext() {
        return index < scoreFrames.size() - 1;
    }

    @Override
    public ScoreFrame next() {
        return null;
    }
}
