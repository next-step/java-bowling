package controller;

import domain.game.BowlingGame;
import domain.game.BowlingGames;
import domain.player.PlayerName;
import domain.player.PlayerNames;
import spark.Request;
import util.StringUtils;

import java.util.*;

import static spark.Spark.post;

public class PlayController extends AbstractController {

    private static final String PLAYER_NAMES_DELIMITER = ",";

    private static final String TEMPLATE_PATH = "play.html";

    public PlayController(BowlingGames games, int currentPlayerIndex) {
        super(games, currentPlayerIndex);
    }

    public void play() {

        post("/start", (req, res) -> {
            PlayerNames playerNames = getPlayerNames(req);

            try {
                createGames(playerNames);
                return render(putAndGetModel(), TEMPLATE_PATH);

            } catch (IllegalArgumentException e) {
                redirectToIndex(res);
                return null;
            }
        });
    }

    private void createGames(PlayerNames playerNames) {
        for (PlayerName curPlayer : playerNames.getNames()) {
            BowlingGame curGame = new BowlingGame(curPlayer);
            games.add(curGame);
        }
    }

    private static PlayerNames getPlayerNames(Request req) {
        return new PlayerNames(Arrays.asList(StringUtils.removeWhitespace(getInputLine(req, "players")).split(PLAYER_NAMES_DELIMITER)));
    }
}


