package bowling.model.state;

import bowling.model.Score;
import bowling.model.state.finishedState.Strike;

public class Start extends State {
    private final static String EXPRESSION = " ";

    @Override
    public State bowling(int fallenPin) {
        score = Score.from(fallenPin);

        if(score.isMaxScore()){
            return Strike.from(score);
        }

        return Open.from(score);
    }

    @Override
    public String toString() {
        return EXPRESSION;
    }
}
