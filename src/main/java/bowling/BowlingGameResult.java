package bowling;

import java.util.List;

public class BowlingGameResult implements GameResult {

  private static final int LIST_INDEX_AND_FRAME_NO_DIFF = 1;
  private static final String DEFAULT_DESC = "";
  private static final int DEFAULT_SCORE = -1;

  private List<FrameResult> framesResult;
  private String playerName;

  public BowlingGameResult(List<FrameResult> frames, String playerName) {
    this.framesResult = frames;
    this.playerName = playerName;
  }

  @Override
  public String frameResult(int frameNo) {
    int listIndex = frameNo - LIST_INDEX_AND_FRAME_NO_DIFF;
    if (hasNoResult(listIndex)) {
      return DEFAULT_DESC;
    }
    return framesResult.get(listIndex).getFrameDesc();
  }

  @Override
  public int scoreResult(int frameNo) {
    int listIndex = frameNo - LIST_INDEX_AND_FRAME_NO_DIFF;
    if (hasNoResult(listIndex)) {
      return DEFAULT_SCORE;
    }
    return framesResult.get(listIndex).getScore();
  }

  @Override
  public String getPlayerName() {
    return this.playerName;
  }

  private boolean hasNoResult(int listIndex) {
    return listIndex >= framesResult.size();
  }
}
