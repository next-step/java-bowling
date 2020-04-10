package bowling.view;

import bowling.application.Response;
import bowling.domain.frame.Result;

public class ResultView {

    public static void view(Response response) {
        BowlingFrameView.getRoundBoard();

        for (Result result : response.getResults()) {
            System.out.println(result.toString());
            System.out.println(result.getTotalScore());
        }
        System.out.println();
        System.out.println();
    }
}
