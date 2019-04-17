package controller;

import domain.game.BowlingGame;
import domain.player.PlayerName;
import domain.player.PlayerNames;
import spark.Request;
import util.StringUtils;

import java.util.Arrays;

import static launcher.WebApplicationLauncher.addGame;
import static launcher.WebApplicationLauncher.clearGames;
import static spark.Spark.post;

public class PlayController extends AbstractController {

    private static final String PLAYER_NAMES_DELIMITER = ",";
    private static final String TEMPLATE_PATH = "play.html";

    public void play() {

        post("/start", (req, res) -> {
            PlayerNames playerNames = getPlayerNames(req);

            try {
                clearGames();
                createGames(playerNames);
                return render(putAndGetModel(), TEMPLATE_PATH);

            } catch (IllegalArgumentException e) {
                redirectToIndex(res);
                return null;
            }
        });
    }

    private PlayerNames getPlayerNames(Request req) {
        return new PlayerNames(Arrays.asList(StringUtils.removeWhitespace(getInputLine(req, "players")).split(PLAYER_NAMES_DELIMITER)));
    }

    private void createGames(PlayerNames playerNames) {
        for (PlayerName curPlayer : playerNames.getNames()) {
            BowlingGame curGame = new BowlingGame(curPlayer);
            addGame(curGame);
        }
    }
}


