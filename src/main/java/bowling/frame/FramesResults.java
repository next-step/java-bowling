package bowling.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FramesResults {

  private List<FramesResult> framesResults;

  public FramesResults() {
    this.framesResults = new ArrayList<>();
  }

  public List<FramesResult> getFramesResults() {
    return Collections.unmodifiableList(framesResults);
  }

  public void addFrameResult(final FramesResult framesResult) {
    framesResults.add(framesResult);
  }

  public boolean isEnd() {
    return frameCount() == framesResults.size();
  }

  private long frameCount() {
    return framesResults
        .stream()
        .filter(FramesResult::isEndOfGame)
        .count();
  }
}