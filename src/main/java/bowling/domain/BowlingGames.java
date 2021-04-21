package bowling.domain;

import bowling.domain.frame.Frames;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {

  private static final String INVALID_PLAYER = "player가 존재하지 않습니다.";
  private final List<BowlingGame> bowlingGames;

  public BowlingGames(List<String> players) {
    this.bowlingGames = players.stream()
        .distinct()
        .map(BowlingGame::new)
        .collect(Collectors.toList());
  }

  public List<BowlingGame> getBowlingGames() {
    return Collections.unmodifiableList(bowlingGames);
  }

  public void play(Player player, Pin count) {
    findBowlingGameByPlayer(player).play(count);
  }

  private BowlingGame findBowlingGameByPlayer(Player player) {
    return bowlingGames.stream()
        .filter(bowlingGame -> bowlingGame.equalToPlayer(player))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(INVALID_PLAYER));
  }

  public Frames getFrames(Player player) {
    return findBowlingGameByPlayer(player).getFrames();
  }

  public boolean isEnd() {
    return this.bowlingGames.stream()
        .allMatch(BowlingGame::isEnd);
  }
}
