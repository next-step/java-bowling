package bowling.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import bowling.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

  @DisplayName("한 프레임안에서 공을 굴린 결과가 스크라이크인 경우를 확인 한다.")
  @Test
  void strike() {
    NormalFrame normalFrame = new NormalFrame(1);
    normalFrame.play(10);
    assertAll(
        () -> assertThat(normalFrame.getScoreMessage()).isEqualTo("X"),
        () -> assertThat(normalFrame.score().scoreValue().getScore()).isEqualTo(10)
    );

  }

  @DisplayName("한 프레임안에서 공을 굴린 결과가 스페어인 경우를 확인 한다.")
  @Test
  void spare() {
    Frame frame = new NormalFrame(1);
    frame.play(4);
    frame.play(6);
    assertAll(
        () -> assertThat(frame.getScoreMessage()).isEqualTo("4|/"),
        () -> assertThat(frame.score().scoreValue().getScore()).isEqualTo(10)
    );
  }

  @DisplayName("한 프레임안에서 공을 굴린 결과가 미스인 경우를 확인 한다.")
  @Test
  void miss() {
    Frame frame = new NormalFrame(1);
    frame.play(4);
    frame.play(5);
    assertAll(
        () -> assertThat(frame.getScoreMessage()).isEqualTo("4|5"),
        () -> assertThat(frame.score().scoreValue().getScore()).isEqualTo(9)
    );
  }

  @DisplayName("이전 스코어가 스트라이크일때 보너스적용이 공 두개 만큼 반영되는지 확인한다.")
  @Test
  void beforeStrikeSumNextFrame() {
    Frame frame = new NormalFrame(1);
    frame.play(10);

    Score firstScore = frame.score();

    Frame frame2 = new NormalFrame(2);
    frame2.play(4);
    frame2.play(4);

    Score secondScore = frame2.frameScoreAdd(firstScore);
    assertThat(secondScore.scoreValue().getScore()).isEqualTo(18);
  }

  @DisplayName("이전 스코어가 스페어일때 보너스적용이 공 한개 만큼 반영되는지 확인한다.")
  @Test
  void beforeSpaceSumNextFrame() {
    Frame frame = new NormalFrame(1);
    frame.play(4);
    frame.play(6);

    Score firstScore = frame.score();

    Frame frame2 = new NormalFrame(2);
    frame2.play(4);
    frame2.play(4);

    Score secondScore = frame2.frameScoreAdd(firstScore);
    assertThat(secondScore.scoreValue().getScore()).isEqualTo(14);
  }
}