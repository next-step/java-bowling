package bowling.domain.frame;

import bowling.domain.result.TotalResult;
import bowling.error.CannotMakeFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

  @ParameterizedTest
  @ValueSource(ints = {0, 11})
  @DisplayName("생성 불가능")
  void invalidCreateTest(int round) {
    assertThatThrownBy(() -> FrameFactory.of(round)).isInstanceOf(CannotMakeFrameException.class);
  }

  @Test
  @DisplayName("전체 진행 테스트")
  void fullGameTest() {
    Frame frame = FrameFactory.of(1);
    frame.bowl(10)
      .bowl(10)
      .bowl(2).bowl(8)
      .bowl(7).bowl(1)
      .bowl(2).bowl(4)
      .bowl(3).bowl(6)
      .bowl(7).bowl(2)
      .bowl(7).bowl(1)
      .bowl(6).bowl(0)
      .bowl(10).bowl(1).bowl(5);

    TotalResult totalResult = frame.showFullResult();

    System.out.print("|");
    totalResult.frameResults().stream().map(frameResult -> String.format("%5s", frameResult.frameView()) + "|").forEach(System.out::print);
    System.out.println();
    System.out.print("|");
    totalResult.frameResults().stream().map(frameResult -> String.format("%5s", frameResult.score()) + "|").forEach(System.out::print);
  }
}