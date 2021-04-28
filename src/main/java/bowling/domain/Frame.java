package bowling.domain;

import java.util.Arrays;

public class Frame {

  private static final String GUTTER_MARK = "-";
  private static final String BLANK = "      ";
  private static final String ONLY_FIRST_SCORE_FORMAT = "  %s   ";
  private static final String SCORE_FORMAT = "  %s|%s ";

  private Pitching first;
  private Pitching second;

  public Frame() {}

  public void play(int hitCount) {
    if (first != null && !first.getResult().isStrike()) {
      playSecondBall(hitCount);
    }
    if (first == null) {
      playFirst(hitCount);
    }
  }

  private void playFirst(int hitCount) {
    this.first = Pitching.firstPitching(hitCount);
  }

  private void playSecondBall(int hitCount) {
    this.second = first.secondPitching(hitCount);
  }

  public int getFirstHit() {
    return first.getHitPins();
  }

  public int getSecondHit() {
    return second.getHitPins();
  }

  private int getTotalHit() {
    return Arrays.asList(first, second).stream()
        .map(Pitching::getHitPins)
        .reduce((a, b) -> a + b)
        .orElse(0);
  }

  public Result getResult() {
    if (first == null) {
      return Result.NONE;
    }
    Result firstResult = first.getResult();
    if (firstResult.isStrike() || second == null) {
      return firstResult;
    }
    return second.getResult();
  }

  public boolean isEnd() {
    if (getResult().isNotMiss()) {
      return true;
    }
    return first != null && second != null;
  }

  public boolean hasBonus() {
    return getResult().isNotMiss();
  }

  public boolean isStrike() {
    return getResult().isStrike();
  }

  public Pitching getFirst() {
    return first;
  }

  public Pitching getSecond() {
    return second;
  }
}
