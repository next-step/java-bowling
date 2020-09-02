package camp.nextstep.edu.rebellion.bowling.domain;

public class Strike implements FrameStatus {
    @Override
    public String makeSymbol() {
        return "+";
    }
}
