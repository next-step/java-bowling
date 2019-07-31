package bowling;

import java.util.Objects;

public class FrameResult {

  private String frameDesc;
  private int score;

  public FrameResult(String desc, int score) {
    this.frameDesc = desc;
    this.score = score;
  }

  public String getFrameDesc() {
    return frameDesc;
  }

  public int getScore() {
    return score;
  }

  @Override
  public String toString() {
    return "FrameResult{" +
        "frameDesc='" + frameDesc + '\'' +
        ", score=" + score +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FrameResult that = (FrameResult) o;
    return score == that.score &&
        Objects.equals(frameDesc, that.frameDesc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(frameDesc, score);
  }

}
