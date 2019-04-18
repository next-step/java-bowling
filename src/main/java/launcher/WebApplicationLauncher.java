package launcher;

import controller.IndexController;
import controller.PlayController;
import controller.ResultController;
import domain.game.BowlingGame;
import domain.game.BowlingGames;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class WebApplicationLauncher {

    private static final BowlingGames games = new BowlingGames();
    private static int currentPlayerIndex = 0;

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/templates");

        new IndexController().index();
        new PlayController().play();
        new ResultController().showResult();
    }

    public static BowlingGames getGames() {
        return games;
    }

    public static void clearGames() {
        games.clear();
        currentPlayerIndex = 0;
    }

    public static void addGame(BowlingGame curGame) {
        games.add(curGame);
    }

    public static BowlingGame getCurrentGame() {
        return games.get(currentPlayerIndex);
    }

    public static void turnToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % games.size();
    }

    public static boolean areAllGamesFinished() {
        return games.areFinished();
    }
}
