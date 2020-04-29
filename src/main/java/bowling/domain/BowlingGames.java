package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {

  private List<BowlingGame> bowlingGames;

  private BowlingGames(List<BowlingGame> bowlingGames) {
    this.bowlingGames = bowlingGames;
  }

  public List<BowlingGame> getBowlingGames() {
    return bowlingGames;
  }

  public static BowlingGames of(Players players) {
    List<BowlingGame> bowlingGames = players.getPlayers()
        .stream()
        .map(BowlingGame::new)
        .collect(Collectors.toList());

    return new BowlingGames(bowlingGames);
  }

  public boolean isEnd() {
    BowlingGame lastGame = bowlingGames.get(bowlingGames.size() - 1);

    return lastGame.isEnd();
  }
}
