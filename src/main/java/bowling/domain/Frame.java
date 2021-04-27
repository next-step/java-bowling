package bowling.domain;

public class Frame {

  public static final String GUTTER_MARK = "-";
  public static final String BLANK = "      ";

  private Pins first;
  private Pins second;
  private Result result = Result.MISS;

  public Frame() {}

  public void play(int hitCount) {
    if (first != null && second == null) {
      playSecondBall(hitCount);
    }

    if (first == null) {
      playFirst(hitCount);
    }
  }

  private void playFirst(int hitCount) {
    this.first = new Pins(hitCount);
    if (first.isClear()) {
      this.result = Result.STRIKE;
    }
  }

  private void playSecondBall(int hitCount) {
    if (this.result.isStrike()) {
      return;
    }

    this.second = new Pins(first.getLeftCount(), hitCount);
    if (second.isClear()) {
      this.result = Result.SPARE;
    }
  }

  public int getFirstHit() {
    return first.getHitPins();
  }

  private String getFirstScoreString() {
    return result.isStrike() ? result.getMark() : getScoreString(first.getHitPins());
  }

  private String getScoreString(int score) {
    return score == 0 ? GUTTER_MARK : String.valueOf(score);
  }

  private String getSecondScoreString() {
    return result.isSpare() ? result.getMark() : getScoreString(second.getHitPins());
  }

  public int getSecondHit() {
    return second.getHitPins();
  }

  private int getTotalHit() {
    if (second == null) {
      return getFirstHit();
    }
    return getFirstHit() + getSecondHit();
  }

  public boolean isStrike() {
    return result.isStrike();
  }

  public boolean isSpare() {
    return result.isSpare();
  }

  public int getBonusBeforeFrame(Result result) {
    if (result.isStrike()) {
      return getTotalHit();
    }
    return first.getHitPins();
  }

  public boolean isEnd() {
    if (result.isNotMiss()) {
      return true;
    }
    return first != null && second != null;
  }

  public String getScoreBoard() {
    if (first == null) {
      return BLANK;
    }

    String firstScoreString = getFirstScoreString();
    if (second == null) {
      return String.format("  %s   ", firstScoreString);
    }

    String secondScoreString = getSecondScoreString();
    return String.format("  %s|%s ", firstScoreString, secondScoreString);
  }
}
