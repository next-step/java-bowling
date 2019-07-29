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
    frames.roll(4);

    BowlingGameResult gameResult = new BowlingGameResult(frames.getFrames());
    assertThat(gameResult.result(1)).isEqualTo("5|4");
    assertThat(gameResult.score(1)).isEqualTo(9);
  }

  @Test
  void Miss일때_스코어를_확인할수있다() {
    Frames frames = new Frames();
    frames.roll(7);
    frames.roll(2);
    frames.roll(3);
    frames.roll(4);
    BowlingGameResult gameResult = new BowlingGameResult(frames.getFrames());
    assertThat(gameResult.score(1)).isEqualTo(9);
    assertThat(gameResult.score(2)).isEqualTo(7);
  }

  @Test
  void 스트라이크일때_점수를_확인할수있다() {
    Frames frames = new Frames();
    frames.roll(10);
    frames.roll(10);
    frames.roll(10);
    frames.roll(10);

    BowlingGameResult gameResult = new BowlingGameResult(frames.getFrames());
    assertThat(gameResult.score(1)).isEqualTo(30);
    assertThat(gameResult.score(2)).isEqualTo(30);
    assertThat(gameResult.score(3)).isEqualTo(-1);
  }

  @Test
  void 스패어일때_점수를_확인할수있다() {
    Frames frames = new Frames();
    frames.roll(5);
    frames.roll(5);
    frames.roll(10);

    BowlingGameResult gameResult = new BowlingGameResult(frames.getFrames());
    assertThat(gameResult.score(1)).isEqualTo(20);
    assertThat(gameResult.score(2)).isEqualTo(-1);
  }


  
}

