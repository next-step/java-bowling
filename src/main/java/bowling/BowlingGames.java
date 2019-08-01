package bowling;

import static java.util.stream.Collectors.toList;

import java.util.List;

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

  public BowlingGame findBowlingGameByName(String playerName) {
    return games.stream()
        .filter(game -> game.isYourGame(playerName))
        .findFirst().orElseThrow(RuntimeException::new);
  }

  public List<GameResult> result() {
    return games.stream()
        .map(BowlingGame::result)
        .collect(toList());
  }

  public boolean isAllGamesEnd() {
    return games.stream()
        .allMatch(bowlingGame -> bowlingGame.isGameEnd() == Boolean.TRUE);
  }

}
