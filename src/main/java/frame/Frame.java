package frame;

import score.ScoreInfo;

import java.util.List;

public interface Frame {

    void bowling(int score);

    boolean isFull();

    List<ScoreInfo> getScoreInfos();
}
