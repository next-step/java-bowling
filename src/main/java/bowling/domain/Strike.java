package bowling.domain;

public class Strike implements FrameStatus {

    private static final String STRIKE = "|  X   ";

    private String display;

    public Strike() {
        this.display = STRIKE;
    }

    @Override
    public String display() {
        return display;
    }
}
