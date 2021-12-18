package bowling.model.gameresult;

public class GameResult {

    private final int frameNo;
    private String stateDesc;

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
}
