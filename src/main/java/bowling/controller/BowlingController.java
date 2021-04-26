package bowling.controller;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.view.InputView;
import bowling.view.ResultView;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingController {

  private static final int FINAL_ROUND = 10;

  public void run() {
    int countOfPlayer = InputView.inputCountOfPlayer();
    List<Player> player = IntStream.range(0, countOfPlayer)
        .mapToObj(i -> new Player(InputView.inputOfPlayerName(i)))
        .collect(Collectors.toList());

    Players players = new Players(player);
    ResultView.showScoreBoard(players);

    for (int i = 0; i < FINAL_ROUND; i++) {
      play(players, i);
    }
  }

  private void play(Players players, int currFrame) {
    players.players().forEach(
        player -> playEachPlayer(players, player, currFrame)
    );
  }

  private void playEachPlayer(Players players, Player player, int currFrame) {
    while (!player.isEndFrame(currFrame)) {
      player.throwBall(InputView.inputCountOfHitPin(player.name()), currFrame);
      ResultView.showScoreBoard(players);
    }
  }
}
