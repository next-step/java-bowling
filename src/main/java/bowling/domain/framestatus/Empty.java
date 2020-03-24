package bowling.domain.framestatus;

public class Empty implements FrameStatus {
    @Override
    public String display() {
        return "";
    }

    @Override
    public int getScore() {
        return 0;
    }
}
