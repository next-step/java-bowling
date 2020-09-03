package camp.nextstep.edu.rebellion.bowling.domain.status;

public class Strike implements FrameStatus {

    @Override
    public String makeSymbol() {
        return "X";
    }
}
