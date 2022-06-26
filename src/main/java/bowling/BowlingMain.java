package bowling;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.LinkedHashMap;
import java.util.Map;

public class BowlingMain {
    private static final int NUMBERS_OF_FRAMES = 10;
    private static final int INITIAL_INDEX = 0;

    public static void main(String[] args) {
        Players players = createPlayers();
        for (int frameIndex = INITIAL_INDEX; frameIndex < NUMBERS_OF_FRAMES; frameIndex++) {
            getPlayersScore(players, frameIndex);
        }
    }

    private static Players createPlayers() {
        int numberOfPlayers = InputView.inputNumberOfPlayers();

        Map<Player, Frames> playerMap = new LinkedHashMap<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            playerMap.put(new Player(InputView.inputPlayerName()), new Frames());
        }
        return new Players(playerMap);
    }

    private static void getPlayersScore(Players players, int frameIndex) {
        Map<Player, Frames> playersMap = players.getPlayers();
        for (Player player : playersMap.keySet()) {
            getEachPlayersScore(frameIndex, player, players);
        }
    }

    private static void getEachPlayersScore(int frameIndex, Player player, Players players) {
        Frame frame = players.getFrame(player, frameIndex);
        frame.bowl(InputView.inputScore(player, frameIndex + 1));
        ResultView.printBowlingGame(players, frameIndex);

        if (frame.capableOfAdditionalBowling()) {
            getEachPlayersScore(frameIndex, player, players);
        }
    }
}
