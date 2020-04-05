package bowling.view;

import bowling.application.Response;
import bowling.domain.state.State;

import java.util.LinkedList;

public class ResultView {

    public static void view(Response response) {
        BowlingFrameView.getRoundBoard();

        LinkedList<State> states = response.getState();

        FrameBoardView.getFrameBoard(states, response.getName());
        ScoreBoardView.getScore(states);

        System.out.println();
        System.out.println();
    }

}
