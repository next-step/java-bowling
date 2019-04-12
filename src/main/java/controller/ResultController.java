package controller;

import domain.game.BowlingGames;
import spark.Request;

import static spark.Spark.post;

public class ResultController extends AbstractController {

    private static final String TEMPLATE_PATH = "play.html";

    public ResultController(BowlingGames games, int currentPlayerIndex) {
        super(games, currentPlayerIndex);
    }

    public void showResult() {
        post("/throw", (req, res) -> {
            if (games.isFinished()) {
                redirectToIndex(res);
                return null;
            }

            try {
                Integer bowl = getBowl(req);
                games.get(currentPlayerIndex).play(bowl);

                if (isFrameFinished(currentPlayerIndex)) {
                    currentPlayerIndex = getNextPlayerIndex();
                }

                return render(putAndGetModel(), TEMPLATE_PATH);

            } catch (IllegalArgumentException e) {
                return render(putAndGetModel(), TEMPLATE_PATH);
            }
        });
    }

    private boolean isFrameFinished(int currentPlayerIndex) {
        return games.get(currentPlayerIndex).getRecentFrame().isFinished();
    }

    private Integer getBowl(Request req) {
        return Integer.parseInt(getInputLine(req, "bowl"));
    }
}


