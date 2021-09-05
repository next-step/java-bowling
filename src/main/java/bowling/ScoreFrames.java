package bowling;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ScoreFrames {
    private static final int START_TURN = 1;

    private final List<ScoreFrame> scoreFrames = Collections.singletonList(
            new NormalScoreFrame(new Turn(START_TURN), FrameResult.MISS));

    public void bowl(int score) {
        ScoreFrame nextScoreFrame = getLastScoreFrame().process(score);

        if (nextScoreFrame != getLastScoreFrame()) {
            scoreFrames.add(nextScoreFrame);
        }
    }

    public int getCurrentTurn() {
        return getLastScoreFrame().getTurnNumber();
    }

    public Iterator<ScoreFrame> iterator() {
        return scoreFrames.iterator();
    }

    private ScoreFrame getLastScoreFrame() {
        return scoreFrames.get(scoreFrames.size() - 1);
    }

}
