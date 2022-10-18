package bowling.domain.state;

public enum StateType {
    STRIKE,
    SPARE,
    MISS,
    STARTED,
    RUNNING;

    public static StateType valueOf(State state) {
        if (state instanceof Strike) {
            return STRIKE;
        }
        if (state instanceof Spare) {
            return SPARE;
        }
        if (state instanceof Miss) {
            return MISS;
        }
        if (state instanceof Running) {
            return RUNNING;
        }
        return STARTED;
    }
}
