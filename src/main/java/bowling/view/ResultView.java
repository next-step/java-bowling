package bowling.view;

import bowling.application.Response;
import bowling.domain.frame.Frame;

import java.util.LinkedList;

public class ResultView {

    public static void view(Response response) {
        BowlingFrameView.getRoundBoard();

        LinkedList<Frame> states = response.getState();
//        FrameBoardView.getFrameBoard(states, response.getName());
//        ScoreBoardView.getScore(states);

        System.out.println();
        System.out.println();
    }

}
