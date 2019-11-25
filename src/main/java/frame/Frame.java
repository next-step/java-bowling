package frame;

import score.ScoreInfo;
import score.framescore.FrameScore;

import java.util.List;

public interface Frame {

    Frame nextFrame();

    void bowling(int score);

    boolean isFull();

    List<ScoreInfo> getScoreInfos();

    FrameScore getFrameScore();

    FrameScore addNextScore(FrameScore before);
}
