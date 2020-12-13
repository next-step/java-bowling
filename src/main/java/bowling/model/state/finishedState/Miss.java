package bowling.model.state.finishedState;

import bowling.model.Score;

public class Miss extends FinishedState {
    public final static String EXPRESSION = " ";

    private Miss(Score previous, Score score){
        super(previous, score);
        this.expression = EXPRESSION;
    }

    public static Miss of(Score previous, Score score){
        return new Miss(previous, score);
    }

}
