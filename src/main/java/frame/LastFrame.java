package frame;

import score.ScoreInfo;
import score.ScoreInfoBundle;

import java.util.ArrayList;
import java.util.List;

import static frame.FrameNumber.LAST_FRAME_NUMBER;

public class LastFrame implements Frame {

    private final FrameNumber frameNumber;
    private final ScoreInfoBundle scores;

    private LastFrame(int frameNumber, List<ScoreInfo> scores) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.scores = new ScoreInfoBundle(scores);
    }

    public static LastFrame init() {
        return new LastFrame(LAST_FRAME_NUMBER, new ArrayList<>());
    }

    @Override
    public void bowling(int score) {
        if (scores.size() == 0 || scores.size() == 2) {
            scores.addLast(ScoreInfo.firstScore(score));
            return;
        }
        if (scores.isStrikeOrStrikeOfLast()) {
            scores.addLast(ScoreInfo.firstScore(score));
            return;
        }
        ScoreInfo scoreInfo = scores.getLast()
                .nextScore(score);
        scores.addLast(scoreInfo);
    }

    @Override
    public boolean isFull() {
        if (scores.size() == 3) {
            return true;
        }

        if (scores.size() < 2) {
            return false;
        }

        if (scores.isStrike()) {
            return false;
        }

        return !scores.isStrikeOrStrikeOfLast();
    }

    @Override
    public List<ScoreInfo> getScoreInfos() {
        return scores.getScoreInfoBundle();
    }
}
