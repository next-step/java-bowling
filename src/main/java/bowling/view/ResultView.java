package bowling.view;

import bowling.application.Response;
import bowling.domain.state.State;

import java.util.List;

public class ResultView {

    public static void view(Response response) {
        BowlingView.getRoundBoard();
        List<State> states = response.getState();
        getFrameBoard(states, response.getName());
        getScore(states);
        System.out.println();
        System.out.println();
    }

    private static void getFrameBoard(List<State> states, String name) {
        System.out.print(String.format("|  %s |", name));
        for (State frame : states) {

        }
        BowlingView.getCollectFrame(states.size());
    }

    private static void getScore(List<State> states) {
        System.out.print("| SCORE|");
        int sum = 0;
        for (State frame : states) {
            if (frame.isFinish()) {

            } else {
                BowlingView.getScoreFrameByEmpty();
            }
        }
        BowlingView.getCollectFrame(states.size());
    }

}
