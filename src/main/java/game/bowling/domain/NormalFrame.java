package game.bowling.domain;

/**
 * Created by yusik on 2019/11/20.
 */
public class NormalFrame implements Frame {

    private final int frameNo;
    private int numberOfPin = 10;
    private int[] scores;
    private FrameStatus status;
    private FrameResult result;

    private NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        scores = new int[2];
        status = FrameStatus.FIRST_THROW;
        result = FrameResult.NONE;
    }

    public static NormalFrame first() {
        return new NormalFrame(1);
    }

    public NormalFrame next() {
        return new NormalFrame(this.frameNo + 1);
    }

    public FinalFrame last() {
        return new FinalFrame(this.frameNo + 1);
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public String getStatus() {
        return result.getFormat(scores[0], scores[1]);
    }

    @Override
    public void bowl(int score) {

        numberOfPin -= score;
        result = nextResult();

        if (result == FrameResult.THROWING) {
            scores[0] = score;
            status = FrameStatus.SECOND_THROW;
        } else {
            scores[1] = score;
            status = FrameStatus.FINISHED;
        }
    }

    public boolean isFinish() {
        return status == FrameStatus.FINISHED;
    }

    private FrameResult nextResult() {

        if (status == FrameStatus.FIRST_THROW && numberOfPin == 0) {
            return FrameResult.STRIKE;
        }

        if (status == FrameStatus.SECOND_THROW && numberOfPin == 0) {
            return FrameResult.SPARE;
        }

        if (status == FrameStatus.SECOND_THROW && numberOfPin > 0 && numberOfPin < 10) {
            return FrameResult.MISS;
        }

        if (status == FrameStatus.SECOND_THROW && numberOfPin == 10) {
            return FrameResult.GUTTER;
        }

        return FrameResult.THROWING;
    }

    public FrameResult getResult() {
        return result;
    }
}
