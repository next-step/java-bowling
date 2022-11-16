package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.domain.Players;
import bowling.dto.PlayerDto;
import bowling.views.InputView;
import bowling.views.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGameApplication {

    public static void main(String[] args) {
        try {
            runApplication();
        } catch (Exception e) {
            OutputView.printError(e);
        }
    }

    private static void runApplication() {
        Players players = InputView.inputPlayers();
        OutputView.printBowlingGames(getPlayerDtos(players));

        for (int i = 0; i < BowlingGame.SIZE_OF_FRAMES; i++) {
            playFrame(players);
        }
    }

    private static void playFrame(Players players) {
        for (int i = 0; i < players.size(); i++) {
            playFrame(players, i);
        }
    }

    private static void playFrame(Players players, int index) {
        Player player = players.get(index);
        while (!player.isCurrentFrameEnded()) {
            int hit = InputView.inputNumberOfPinsHit(player.getName());
            player.hit(hit);
            OutputView.printBowlingGames(getPlayerDtos(players));
        }
        player.moveNextFrame();
    }

    private static List<PlayerDto> getPlayerDtos(Players players) {
        return IntStream.range(0, players.size())
                .mapToObj(i -> PlayerDto.from(players.get(i)))
                .collect(Collectors.toList());
    }

}
