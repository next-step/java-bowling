package bowling_step4.domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGamesTest {

  @Test
  @DisplayName("play 확인")
  void play() {
    BowlingGames bowlingGames = new BowlingGames(Arrays.asList("mdy","guu"));
    Player player = new Player("mdy");
    bowlingGames.play(player, new Pin(4));

    assertThat(bowlingGames.getFrames(player).size()).isEqualTo(1);
  }

  @Test
  @DisplayName("isEnd 확인")
  void isEndReturnFalse() {
    BowlingGames bowlingGames = new BowlingGames(Arrays.asList("mdy", "guu"));
    Player player = new Player("mdy");

    for (int i = 0; i < 12; i++) {
      bowlingGames.play(player, new Pin(10));
    }

    assertThat(bowlingGames.isEnd()).isFalse();
  }

  @Test
  @DisplayName("isEnd 확인")
  void isEndReturnTrue() {
    BowlingGames bowlingGames = new BowlingGames(Arrays.asList("mdy", "guu"));
    Player player = new Player("mdy");
    Player player2 = new Player("guu");

    for (int i = 0; i < 12; i++) {
      bowlingGames.play(player, new Pin(10));
      bowlingGames.play(player2, new Pin(10));
    }

    assertThat(bowlingGames.isEnd()).isTrue();
  }
}
