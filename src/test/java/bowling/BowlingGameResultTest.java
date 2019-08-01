package bowling;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BowlingGameResultTest {

  GameResult gameResult;

  @BeforeEach
  void 생성() {
    String playerName = "LCJ";
    Frames frames = new Frames();
    frames.bowl(5);
    frames.bowl(4);
    gameResult = frames.getResult(playerName);
  }

  @Test
  void 플레이어명을_확인한다() {
    assertThat(gameResult.getPlayerName()).isEqualTo("LCJ");
  }

  @Test
  void 게임결과가_없을경우_default값이_리턴된다() {
    assertThat(gameResult.frameResult(2)).isEqualTo("");
  }

  @Test
  void 게임스코어가_없을경우_default값이_리턴된다() {
    assertThat(gameResult.scoreResult(2)).isEqualTo(-1);
  }

  @Test
  void 해당프레임의_결과를_리턴한다() {
    assertThat(gameResult.frameResult(1)).isEqualTo("5|4");
  }

  @Test
  void 해당프레임의_스코어를_리턴한다() {
    assertThat(gameResult.scoreResult(1)).isEqualTo(9);
  }
}