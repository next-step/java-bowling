package bowling_step4.domain.frame;

import bowling_step4.domain.Pin;
import bowling_step4.domain.Score;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

  @ParameterizedTest
  @CsvSource(value = {"5,5,2", "10,10,3"})
  @DisplayName("프레임 갯수 확인")
  void play(int first, int second, int expect) {
    Frames frames = new Frames();
    frames.play(new Pin(first));
    frames.play(new Pin(second));

    assertThat(frames.getFrames()).hasSize(expect);
  }


  @Test
  @DisplayName("프레임 갯수는 최대 10개이다.")
  void tenFrame() {
    Frames frames = new Frames();
    for (int i = 0; i < 12; i++) {
      frames.play(new Pin(10));
    }

    assertThat(frames.getFrames()).hasSize(10);
  }


  @Test
  @DisplayName("점수 계산")
  void calculate() {
    Frames frames = new Frames();
    frames.play(new Pin(5));
    frames.play(new Pin(2));
    frames.play(new Pin(2));
    frames.play(new Pin(2));

    List<Integer> scores = frames.getScores().stream()
        .map(Score::getScore)
        .collect(Collectors.toList());

    assertThat(scores).containsExactly(7, 11);
  }

}
