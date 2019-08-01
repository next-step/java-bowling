package bowling;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

public class BowlingGames {

  private List<BowlingGame> games;

  public BowlingGames(List<String> playersName) {
    games = playersName.stream()
        .map(BowlingGame::new)
        .collect(toList());
  }

  public int gameCount() {
    return games.size();
  }

  public Optional<BowlingGame> findBowlingGameByName(String playerName) {
    return games.stream()
        .filter(game -> game.isYourGame(playerName))
        .findFirst();
  }

  public List<GameResult> result() {
    return games.stream()
        .map(BowlingGame::result)
        .collect(toList());
  }
}
