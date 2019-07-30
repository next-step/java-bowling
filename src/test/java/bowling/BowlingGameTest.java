package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {

  BowlingGame game;

  @BeforeEach
  void 게임생성() {
    game = new BowlingGame();
  }

  @Test
  void Bowl한_결과로_다음_프레임을_가지고온다() {
    assertThat(game.bowl(5)).isInstanceOf(Frame.class);
  }

  @Test
  void 게임의결과로_GameResult를_반환한다() {
    assertThat(game.result()).isInstanceOf(GameResult.class);
  }

  @Test
  void 프레임별_Bowl의_결과를_확인할수있다() {
    game.bowl(5);
    game.bowl(5);
    game.bowl(10);
    game.bowl(3);
    game.bowl(0);
    game.bowl(2);
    game.bowl(8);


    GameResult result = game.result();

    assertThat(result.frameResult(1)).isEqualTo("5|/");
    assertThat(result.frameResult(2)).isEqualTo("X");
    assertThat(result.frameResult(3)).isEqualTo("3|-");
    assertThat(result.frameResult(4)).isEqualTo("2|/");

  }

}
