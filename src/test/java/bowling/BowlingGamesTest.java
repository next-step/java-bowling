package bowling;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowlingGamesTest {

  BowlingGames games;

  @BeforeEach
  void 생성() {
    List<String> names = Lists.list("LCJ", "PYH");
    games = new BowlingGames(names);
  }

  @Test
  void 생성시에_플레이어_수만큼_BowlingGame이_생성된다() {

    assertThat(games.gameCount()).isEqualTo(2);
  }

  @Test
  void 플레이어가없을때는_BowlingGame이_생성이_안된다() {
    List<String> names = new ArrayList<>();
    BowlingGames games = new BowlingGames(names);
    assertThat(games.gameCount()).isEqualTo(0);
  }

  @Test
  void PlayerName으로_BowlingGame을_찾는다() {
    String playerName = "LCJ";
    BowlingGame bowlingGame = games.findBowlingGameByName(playerName);
    assertThat(bowlingGame).isEqualTo(new BowlingGame("LCJ"));
  }

  @Test
  void 결과로_사람수만큼_BowlingGameReulst_List를_반환한다() {
    List<GameResult> results = games.result();

    assertThat(results).isInstanceOf(List.class);
    assertThat(results).hasSize(2);
  }


}
