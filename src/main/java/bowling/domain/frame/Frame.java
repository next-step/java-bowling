package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.record.Record;
import bowling.domain.score.Score;

import static bowling.utils.BowlingConstants.*;


public interface Frame {

    static Frame generateNextFrame(int currFrame) {
        if(currFrame > MAX_FRAME_COUNT) {
            throw new IllegalArgumentException("프레임은 최대 10을 넘길 수 없음");
        }

        if(currFrame == MAX_FRAME_COUNT) {
            return new FinalFrame(currFrame);
        }

        return new NormalFrame(currFrame);
    }

    Frame rollBowlingBall(Pin pin);

    boolean isFinished();

    boolean isLastFrame();

    Record recordFrameResult(Pin pin) ;

    int getCurrFrame();

    Records getRecords();

    Score calculateScore();
    Score calculateBonus(Score score);
}
