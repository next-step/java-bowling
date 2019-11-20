package frame;

import score.ScoreInfo;

import java.util.List;

public class LastFrame implements Frame {
    @Override
    public NormalFrame nextFrame(List<ScoreInfo> scores) {
        return null;
    }

    @Override
    public void bowling(int score) {

    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public List<ScoreInfo> getScoreInfos() {
        return null;
    }
}
