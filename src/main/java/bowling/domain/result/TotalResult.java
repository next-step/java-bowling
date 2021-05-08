package bowling.domain.result;

import java.util.ArrayList;
import java.util.List;

public class TotalResult {
  private final List<FrameResult> frameResults;

  public TotalResult() {
    this.frameResults = new ArrayList<>();
  }

  public void add(FrameResult frameResult) {
    frameResults.add(frameResult);
  }

  public List<FrameResult> frameResults() {
    return frameResults;
  }
}
