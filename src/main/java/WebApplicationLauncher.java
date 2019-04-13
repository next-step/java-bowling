import controller.IndexController;
import controller.PlayController;
import controller.ResultController;
import domain.game.BowlingGames;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class WebApplicationLauncher {

    private static final BowlingGames games = new BowlingGames();
    private static final int currentPlayerIndex = 0;

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/templates");

        IndexController.index();
        new PlayController(games, currentPlayerIndex).play();
        new ResultController(games, currentPlayerIndex).showResult();
    }
}
