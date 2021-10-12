package bowling.domain;

public class FrameResult {
    private final String indication;

    public FrameResult(final String indication) {
        this.indication = indication;
    }

    public String showIndication() {
        return indication;
    }
}