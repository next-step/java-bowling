package camp.nextstep.edu.rebellion.bowling.domain;

public class Spare implements FrameStatus {
    private final int first;
    private final int last;

    public Spare(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public String makeSymbol() {
        return first + "|/";
    }
}
