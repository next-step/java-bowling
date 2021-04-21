package bowling.domain;

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
    bowlingGames.play(player, 10);

    assertThat(bowlingGames.getFrames(player).size()).isEqualTo(1);
  }
}
