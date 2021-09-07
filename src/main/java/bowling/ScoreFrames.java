package bowling;

import java.util.*;

public class ScoreFrames implements Iterable<ScoreFrame> {
    private static final int START_TURN = 1;
    private static final int END_TURN = 10;

    private final List<ScoreFrame> scoreFrames = new LinkedList<>();    // sequential access

    public ScoreFrames() {
        scoreFrames.add(new NormalScoreFrame(new Turn(START_TURN), FrameResult.MISS));
    }

    public void bowl(int score) {
        ScoreFrame nextScoreFrame = getLastScoreFrame().process(score);

        if (nextScoreFrame != getLastScoreFrame()) {
            scoreFrames.add(nextScoreFrame);
        }
    }

    public int getCurrentTurn() {
        return getLastScoreFrame().getTurnNumber();
    }

    public boolean isContinued() {
        return getCurrentTurn() <= END_TURN;
    }

    private ScoreFrame getLastScoreFrame() {
        return scoreFrames.get(scoreFrames.size() - 1);
    }

    @Override
    public Iterator<ScoreFrame> iterator() {
        return scoreFrames.iterator();
    }
}
