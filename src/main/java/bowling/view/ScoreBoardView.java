package bowling.view;

import bowling.domain.state.Bonus;
import bowling.domain.state.State;

import java.util.LinkedList;
import java.util.Objects;

public class ScoreBoardView {

    public static void getScore(LinkedList<State> states) {
        System.out.print("| SCORE|");
        int sum = 0;
        int count = 0;
        for (State state : states) {
            if (state instanceof Bonus) {
                break;
            }

            if (Objects.nonNull(state.getScore()) && state.getScore().isCalculation()) {
                if (state.isFinish()) {
                    sum += state.getScore().getScore();
                    count++;
                    BowlingFrameView.getScoreFrame(sum);
                } else {
                    BowlingFrameView.getScoreFrameByEmpty();
                }
            }
        }
        BowlingFrameView.getCollectFrame(count);
    }

}
