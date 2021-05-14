package bowling;

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
        PlayerCount playerCount = getPlayerCount();
        Players players = new Players(getPlayers(playerCount));

        Player player = getPlayer(playerCount);
        RESULT_VIEW.printScoreBoard(player);
        while (!player.isFinish()) {
            playBowl(player);
            RESULT_VIEW.printScoreBoard(player);
        }
        RESULT_VIEW.printResult(player);
    }

    private static final List<Player> getPlayers(PlayerCount playerCount) {
        final List<Player> players = new ArrayList<>();
        while (!playerCount.hasNext()) {
            System.out.println("언제 종료됨?");
            players.add(getPlayer(playerCount));
            playerCount = playerCount.next();
        }
        return players;
    }

    private static final PlayerCount getPlayerCount() {
        try {
            return new PlayerCount(INPUT_VIEW.InputPlayerCountByConsole());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getPlayerCount();
        }
    }

    private static final void playBowl(final Player player) {
        try {
            player.bowl(INPUT_VIEW.InputFallCountByConsole(player.name()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            playBowl(player);
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
