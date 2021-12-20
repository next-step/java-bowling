package bowling.model.gameresult;

public class GameResult {

    private static final int FINAL_FRAME_NO = 10;
    private final int frameNo;
    private final String stateDesc;

    public GameResult(int frameNo, String stateDesc) {
        this.frameNo = frameNo;
        this.stateDesc = stateDesc;
    }

    public int getFrameNo() {
        return this.frameNo;
    }

    public String getStatusDesc() {
        return this.stateDesc;
    }

    public boolean isFinalResult() {
        return this.frameNo == FINAL_FRAME_NO;
    }
}
