package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerCount;
import bowling.domain.player.Players;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public final class Bowling {

    private static final InputView INPUT_VIEW;
    private static final ResultView RESULT_VIEW;

    static {
        INPUT_VIEW = InputView.getInstance();
        RESULT_VIEW = ResultView.getInstance();
    }

    public static void main(String[] args) {
        final PlayerCount playerCount = getPlayerCount();
        final Players players = getPlayers(playerCount);
        playBowlingGame(players);
    }

    private static final Players getPlayers(final PlayerCount playerCount) {
        try {
            return new Players(getPlayerList(playerCount));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getPlayers(playerCount);
        }
    }

    private static final PlayerCount getPlayerCount() {
        try {
            return new PlayerCount(INPUT_VIEW.InputPlayerCountByConsole());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getPlayerCount();
        }
    }

    private static final List<Player> getPlayerList(final PlayerCount playerCount) {
        final List<Player> players = new ArrayList<>();
        PlayerCount generateCount = playerCount;
        while (generateCount.hasNext()) {
            players.add(getPlayer(generateCount));
            generateCount = generateCount.next();
        }
        return players;
    }

    private static final void playBowlingGame(final Players players) {
        RESULT_VIEW.printScoreBoard(players);
        while (!players.isAllFinish()) {
            players.stream().forEach(player -> playBowl(player, players));
        }
        RESULT_VIEW.printEndGame();
    }

    private static final void playBowl(final Player player, final Players players) {
        final Frame frame = player.current();
        while (!frame.isFinish()) {
            bowl(player);
            RESULT_VIEW.printScoreBoard(players);
        }
    }

    private static final void bowl(final Player player) {
        try {
            player.bowl(INPUT_VIEW.InputFallCountByConsole(player.name()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            bowl(player);
        }
    }

    private static final Player getPlayer(final PlayerCount playerCount) {
        try {
            return new Player(INPUT_VIEW.InputPlayerNameByConsole(playerCount));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getPlayer(playerCount);
        }
    }
}
