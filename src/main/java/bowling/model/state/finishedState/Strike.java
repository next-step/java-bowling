package bowling.model.state.finishedState;

import bowling.model.Score;

public class Strike extends FinishedState {
    public final static String EXPRESSION = "X";

    private Strike(Score previous, Score score){
        super(previous, score);
    }

    public static Strike of(Score previous, Score score){
        return new Strike(previous, score);
    }

    public static Strike from(Score score){
        return new Strike(Score.from(0), score);
    }

    @Override
    public String toString() {
        return EXPRESSION;
    }
}
