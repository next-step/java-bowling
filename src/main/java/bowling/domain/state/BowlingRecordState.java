package bowling.domain.state;

public enum BowlingRecordState {
    STRIKE,
    SPARE,
    MISS,
    STARTED,
    RUNNING;

    public static BowlingRecordState valueOf(State state){
        if(state instanceof Strike){
            return STRIKE;
        }
        if(state instanceof Spare){
            return SPARE;
        }
        if(state instanceof Miss){
            return MISS;
        }
        if(state instanceof Started){
            return STARTED;
        }
        return RUNNING;
    }
}
