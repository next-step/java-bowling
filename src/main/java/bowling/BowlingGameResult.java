package bowling;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGameResult implements GameResult {

  private static final int LIST_INDEX_AND_FRAME_NO_DIFF = 1;
  private static final String DEFAULT_DESC = "";

  private List<String> framesResult;

  public BowlingGameResult(LinkedList<Frame> frames) {
    framesResult = frames.stream()
        .map(Frame::desc)
        .collect(Collectors.toList());
  }

  @Override
  public String frameResult(int frameNo) {
    int listIndex = frameNo - LIST_INDEX_AND_FRAME_NO_DIFF;
    if (hasNoResult(listIndex)) {
      return DEFAULT_DESC;
    }
    return framesResult.get(listIndex);
  }

  private boolean hasNoResult(int listIndex) {
    return listIndex >= framesResult.size();
  }
}
