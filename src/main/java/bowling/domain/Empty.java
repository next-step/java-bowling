package bowling.domain;

public class Empty implements FrameStatus {
    @Override
    public String display() {
        return "";
    }
}
