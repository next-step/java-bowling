package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FramesTest {

  Frames frames;

  @BeforeEach
  void 생성() {
    frames = new Frames();
  }

  @Test
  void 점수계산이_끝난_Score를_반환한다_STRIKE() {
    frames.bowl(10);
    frames.bowl(5);
    frames.bowl(3);
    assertThat(frames.score(1)).isEqualTo(new Score(18, 0));
  }

  @Test
  void 점수계산이_끝난_Score를_반환한다_Spare() {
    frames.bowl(4);
    frames.bowl(6);

    frames.bowl(5);
    frames.bowl(3);
    assertThat(frames.score(1)).isEqualTo(new Score(15, 0));
  }

  @Test
  void 점수계산이_끝난_Score를_반환한다_Miss() {
    frames.bowl(3).bowl(3);
    assertThat(frames.score(1)).isEqualTo(new Score(6, 0));
  }

  @Test
  void BowlingGameResult생성시_플레이어명도_전달한다() {
    String playerName = "LCJ";
    GameResult result = frames.getResult(playerName);

    assertThat(result.getPlayerName()).isEqualTo(playerName);

  }
}