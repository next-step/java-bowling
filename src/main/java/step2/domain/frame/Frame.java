package step2.domain.frame;

import step2.domain.Score;

public interface Frame {

    int DEFAULT = 0;
    int FINAL_FRAME_NO = 10;
    int MAX_SCORE = 10;

    Frame bowl(int fallingPins);

    Score calculateAdditionalScore(Score beforeScore);

}
