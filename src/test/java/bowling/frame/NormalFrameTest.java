package bowling.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.dto.FrameDto;
import bowling.score.Score;
import bowling.score.ScoreBoard;
import bowling.score.ScoreResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

  @DisplayName("한 프레임안에서 공을 굴린 결과가 스크라이크인 경우를 확인 한다.")
  @Test
  void strike() {
    NormalFrame normalFrame = new NormalFrame(1);
    normalFrame
        .play(10);

    assertThat(normalFrame.getScoreMessage()).isEqualTo("X");
  }

  @DisplayName("한 프레임안에서 공을 굴린 결과가 스페어인 경우를 확인 한다.")
  @Test
  void spare() {
    Frame frame = new NormalFrame(1);
    frame.play(4)
        .play(6);

    assertThat(frame.getScoreMessage()).isEqualTo("4|/");
  }

  @DisplayName("한 프레임안에서 공을 굴린 결과가 미스인 경우를 확인 한다.")
  @Test
  void miss() {
    Frame frame = new NormalFrame(1);
    frame.play(4);
    frame.play(5);
    assertThat(frame.getScoreMessage()).isEqualTo("4|5");
  }

  @DisplayName("이전 스코어가 스트라이크일때 보너스적용이 공 두개 만큼 반영되는지 확인한다.")
  @Test
  void beforeStrikeSumNextFrame() {
    Frame frame = new NormalFrame(1);
    frame.play(8)
        .play(0);

    Score score = frame.frameScoreAdd(Score.strike());
    assertThat(score.from().getScore()).isEqualTo(18);
  }

  @DisplayName("이전 스코어가 스페어일때 보너스적용이 공 한개 만큼 반영되는지 확인한다.")
  @Test
  void beforeSpaceSumNextFrame() {
    Frame frame = new NormalFrame(1);
    frame.play(10);

    Score score = frame.frameScoreAdd(Score.spare());
    assertThat(score.from().getScore()).isEqualTo(20);
  }

  @DisplayName("프래임별 스코어 객체생성을 하여 전체 스코어를 누적 합산하는 스코어보드 객체를 생성한다.")
  @Test
  void createScoreBoard() {
    NormalFrame frame = new NormalFrame(1);
    frame
        .play(10)
        .play(10)
        .play(10)
        .play(10)
        .play(10)
        .play(10)
        .play(10)
        .play(10)
        .play(10)
        .play(10)
        .play(10)
        .play(10);

    ScoreBoard scoreBoard = frame.createScoreBoard();
    List<ScoreResult> scoreResults = scoreBoard.getScoreResults();
    FrameDto frameDto = scoreResults.get(scoreResults.size() - 1).from();

    assertThat(frameDto.getTotalScore()).isEqualTo(300);
  }
}