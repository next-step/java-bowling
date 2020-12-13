package bowling.model.state.finishedState;

import bowling.model.Score;

public class Gutter extends FinishedState {
    private final static String EXPRESSION = "-";

    private Gutter(Score previous, Score score){
        super(previous, score);
        this.expression = EXPRESSION;
    }

    public static Gutter of(Score previous, Score score){
        return new Gutter(previous, score);
    }
}
