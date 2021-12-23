package bowling.domain.result;

public class Result {

    private static final int FINAL_FRAME_NO = 10;
    private final int frameNo;
    private final String stateMark;

    public Result(int frameNo, String stateMark) {
        this.frameNo = frameNo;
        this.stateMark = stateMark;
    }

    public int getFrameNo() {
        return this.frameNo;
    }

    public String getStatusMark() {
        return this.stateMark;
    }

    public boolean isFinalResult() {
        return this.frameNo == FINAL_FRAME_NO;
    }
}
