package controller;

import launcher.WebApplicationLauncher;
import spark.Request;

import static launcher.WebApplicationLauncher.getCurrentGame;
import static spark.Spark.post;

public class ResultController extends AbstractController {

    private static final String TEMPLATE_PATH = "play.html";

    public void showResult() {
        post("/throw", (req, res) -> {
            if (WebApplicationLauncher.areAllGamesFinished()) {
                redirectToIndex(res);
                return null;
            }

            try {
                Integer bowl = getBowl(req);
                getCurrentGame().play(bowl);

                if (isFrameFinished()) {
                    WebApplicationLauncher.turnToNextPlayer();
                }

                return render(putAndGetModel(), TEMPLATE_PATH);

            } catch (IllegalArgumentException e) {
                return render(putAndGetModel(), TEMPLATE_PATH);
            }
        });
    }

    private boolean isFrameFinished() {
        return getCurrentGame().getRecentFrame().isFinished();
    }

    private Integer getBowl(Request req) {
        return Integer.parseInt(getInputLine(req, "bowl"));
    }
}


