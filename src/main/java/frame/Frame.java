package frame;

import score.ScoreInfo;

import java.util.List;

public interface Frame {

    NormalFrame nextFrame(List<ScoreInfo> scores);

    void bowling(int score);

    boolean isFull();

    List<ScoreInfo> getScoreInfos();
}
