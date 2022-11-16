package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.dto.PlayerDto;
import bowling.views.InputView;
import bowling.views.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGameApplication {

    public static void main(String[] args) {
        try {
            runApplication();
        } catch (Exception e) {
            OutputView.printError(e);
        }
    }

    private static void runApplication() {
        List<Player> players = InputView.inputPlayers();
        OutputView.printBowlingGames(getPlayerDtos(players));

        for (int i = 0; i < BowlingGame.SIZE_OF_FRAMES; i++) {
            playFrameTurn(players);
        }
    }

    private static void playFrameTurn(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            playFrameTurn(players, i);
        }
    }

    private static void playFrameTurn(List<Player> players, int index) {
        Player player = players.get(index);
        while (!player.isCurrentFrameEnded()) {
            int hit = InputView.inputNumberOfPinsHit(player.getName());
            player.hit(hit);
            OutputView.printBowlingGames(getPlayerDtos(players));
        }
        player.moveNextFrame();
    }

    private static List<PlayerDto> getPlayerDtos(List<Player> players) {
        return players.stream()
                .map(PlayerDto::from)
                .collect(Collectors.toList());
    }

}
