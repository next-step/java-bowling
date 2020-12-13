package bowling.model.state.finishedState;

import bowling.model.Score;

public class Spare extends FinishedState {
    private final static String EXPRESSION = "/";

    private Spare(Score previous, Score score){
        super(previous, score);
        this.expression = EXPRESSION;
    }

    public static Spare of(Score previous, Score score){
        return new Spare(previous, score);
    }
}
