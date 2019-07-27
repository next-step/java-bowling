package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowlingGameResultTest {

  BowlingGameResult gameResult;

  @BeforeEach
  void 생성() {
    Frames frames = new Frames();
    frames.roll(5);
    frames.roll(5);

    gameResult = new BowlingGameResult(frames.getFrames());
  }

  @Test
  void 결과입력_및_조회() {
    assertThat(gameResult.result(1)).isEqualTo("5|/");
  }

  @Test
  void Frames로_생성확인() {
    Frames frames = new Frames();
    frames.roll(5);
    frames.roll(5);

    BowlingGameResult gameResult = new BowlingGameResult(frames.getFrames());
    assertThat(gameResult.result(1)).isEqualTo("5|/");
  }

}
