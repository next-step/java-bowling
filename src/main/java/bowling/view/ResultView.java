package bowling.view;

import bowling.application.Response;
import bowling.domain.frame.Result;

public class ResultView {

    public static void view(Response response) {
        BowlingFrameView.getRoundBoard();
        BowlingFrameView.getName(response.getName());

        for (Result result : response.getResults()) {
            int length = result.getDisplay().length();
            if (length > 1) {
                BowlingFrameView.getFinishFrame(result.getDisplay());
            } else {
                BowlingFrameView.getReadyFrame(result.getDisplay());
            }

        }
        BowlingFrameView.getCollectFrame(response.getResults().size());

        BowlingFrameView.getName("   ");
        for (Result result : response.getResults()) {
            BowlingFrameView.getScoreFrame(result.getTotalScore());
        }

        BowlingFrameView.getCollectFrame(response.getResults().size());
        System.out.println();
        System.out.println();
    }
}
