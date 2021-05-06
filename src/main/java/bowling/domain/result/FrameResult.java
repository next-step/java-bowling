package bowling.domain.result;

public class FrameResult {
  private static final String EMPTY = "";

  private final String frameView;
  private final int score;
  private final boolean scoreVisible;

  public FrameResult(String frameView, int score, boolean scoreVisible) {
    this.frameView = frameView;
    this.score = score;
    this.scoreVisible = scoreVisible;
  }

  public String frameView() {
    return frameView;
  }

  public String score() {
    if (scoreVisible) {
      return String.valueOf(score);
    }
    return EMPTY;
  }

  public boolean isScoreVisible(){
    return scoreVisible;
  }

  public FrameResult addScore(int additionalScore, boolean scoreVisible) {
    return new FrameResult(frameView, score + additionalScore, scoreVisible);
  }
}
