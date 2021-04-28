package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Bowling {

  public static final int BASIC_FRAME = 10;
  private static final int MAX_FRAME = 12;

  private String player;
  private List<Frame> frames = new ArrayList<>();


  public Bowling(String player) {
    this.player = player;
    IntStream.range(0, BASIC_FRAME).forEach(i -> frames.add(new Frame()));
  }

  public int size() {
    return frames.size();
  }

  public void play(int hitCount) {
    frames.stream()
        .filter(frame -> !frame.isEnd())
        .findFirst()
        .orElseGet(() -> addBonusFrame())
        .play(hitCount);
  }

  public Frame getFrame(int frameNumber) {
    int frameIndex = frameNumber - 1;
    return frames.get(frameIndex);
  }

  public Frame getCurrentFrame() {
    return frames.stream()
        .filter(frame -> !frame.isEnd())
        .findFirst()
        .orElseGet(() -> frames.get(frames.size() - 1));
  }

  public int getCurrentFrameNumber() {
    Integer currentIndex = frames.stream()
        .filter(frame -> !frame.isEnd())
        .findFirst()
        .map(frame -> frames.indexOf(frame))
        .orElseGet(() -> frames.size());
    return currentIndex + 1;
  }

  public boolean hasBonus() {
    Frame frame = getFrame(BASIC_FRAME);
    return frame.hasBonus();
  }

  public boolean isEnd() {
    if (getCurrentFrameNumber() <= BASIC_FRAME) {
      return false;
    }
    if (frames.size() >= MAX_FRAME) {
      return true;
    }
    if (frames.size() > BASIC_FRAME) {
      return isBonusEnd();
    }
    return !hasBonus();
  }

  private boolean isBonusEnd() {
    if (getFrame(frames.size()).isStrike() && getFrame(BASIC_FRAME).isStrike()) {
      return false;
    }
    return true;
  }

  public Frame addBonusFrame() {
    Frame bonusFrame = new Frame();
    frames.add(bonusFrame);
    return bonusFrame;
  }

  public String getPlayer() {
    return player;
  }

  public List<Frame> getFrames() {
    return frames;
  }
}
