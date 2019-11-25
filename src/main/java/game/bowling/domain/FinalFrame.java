package game.bowling.domain;

/**
 * Created by yusik on 2019/11/20.
 */
public class FinalFrame implements Frame {

    private final int frameNo;
    private int numberOfPin = 10;
    private int[] scores;
    private FrameStatus status;
    private FrameResult result;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        scores = new int[3];
        status = FrameStatus.FIRST_THROW;
        result = FrameResult.NONE;
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public String getStatus() {
//        return result.getFormat(scores[0], scores[1]);
        return "";
    }

    @Override
    public void bowl(int score) {
        numberOfPin -= score;
        result = nextResult();

        if (status == FrameStatus.FIRST_THROW) {
            scores[0] = score;
            status = FrameStatus.SECOND_THROW;
        } else if (canThrowThird() && status == FrameStatus.SECOND_THROW) {
            scores[1] = score;
            status = FrameStatus.THIRD_THROW;
        } else if (status == FrameStatus.THIRD_THROW) {
            scores[2] = score;
            status = FrameStatus.FINISHED;
        } else {
            scores[1] = score;
            status = FrameStatus.FINISHED;
        }
    }

    private boolean canThrowThird() {
        return result == FrameResult.STRIKE || result == FrameResult.SPARE;
    }

    @Override
    public boolean isFinish() {
        return status == FrameStatus.FINISHED;
    }

    private FrameResult nextResult() {

        if (status == FrameStatus.FIRST_THROW && numberOfPin == 0) {
            numberOfPin = 10;
            return FrameResult.STRIKE;
        }

        if (result == FrameResult.STRIKE && status == FrameStatus.SECOND_THROW && numberOfPin == 0) {
            numberOfPin = 10;
            return FrameResult.STRIKE;
        }

        if (result == FrameResult.STRIKE && status == FrameStatus.THIRD_THROW && numberOfPin == 0) {
            numberOfPin = 10;
            return FrameResult.STRIKE;
        }

        if (result != FrameResult.STRIKE && status == FrameStatus.SECOND_THROW && numberOfPin == 0) {
            numberOfPin = 10;
            return FrameResult.SPARE;
        }

        return FrameResult.MISS;
    }

    public FrameResult getResult() {
        return result;
    }
}
