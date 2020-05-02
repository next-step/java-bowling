package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameResults {

  private List<FrameResult> frameResults = new ArrayList<>();

  public FrameResults() { }

  public List<FrameResult> getFrameResults() {
    return Collections.unmodifiableList(frameResults);
  }

  public void add(FrameResult frameResult) {
    frameResults.add(frameResult);
  }
}
