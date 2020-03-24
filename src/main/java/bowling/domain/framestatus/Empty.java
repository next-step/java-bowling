package bowling.domain.framestatus;

public class Empty implements FrameStatus {
    @Override
    public String display() {
        return "";
    }

    @Override
    public int getPreScore() {
        return 0;
    }

    @Override
    public int getCurrentScore() {
        return 0;
    }

    @Override
    public boolean isBonus() {
        return false;
    }
}
