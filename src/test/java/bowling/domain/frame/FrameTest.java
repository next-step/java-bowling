package bowling.domain.frame;

import bowling.domain.result.TotalResult;
import bowling.domain.turn.FallenPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrameTest {

  @Test
  @DisplayName("전체 진행 테스트")
  void fullGameTest(){
    Frame frame = Frame.of(1);
    frame.bowl(new FallenPins(10))
      .bowl(new FallenPins(10))
      .bowl(new FallenPins(2)).bowl(new FallenPins(8))
      .bowl(new FallenPins(7)).bowl(new FallenPins(1))
      .bowl(new FallenPins(2)).bowl(new FallenPins(4))
      .bowl(new FallenPins(3)).bowl(new FallenPins(6))
      .bowl(new FallenPins(7)).bowl(new FallenPins(2))
      .bowl(new FallenPins(7)).bowl(new FallenPins(1))
      .bowl(new FallenPins(6)).bowl(new FallenPins(0))
      .bowl(new FallenPins(10)).bowl(new FallenPins(1));

    TotalResult totalResult = frame.showFullResult();

    System.out.print("|");
    totalResult.frameResults().stream().map(frameResult -> String.format("%4s", frameResult) + "|").forEach(System.out::print);
  }
}