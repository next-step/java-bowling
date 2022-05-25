package bowling.domain;

public class Ready extends RunningState {
    private static final String READY_SYMBOL = "";

    private Ready() {
    }

    public static State create() {
        return new Ready();
    }

    @Override
    public State bowl(Pitching pitching) {
        if (pitching.isStrike()) {
            return new Strike();
        }
        return new FirstPitching(pitching);
    }

    @Override
    public String symbol() {
        return READY_SYMBOL;
    }
}
