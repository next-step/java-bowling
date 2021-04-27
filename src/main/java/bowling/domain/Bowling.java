package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Bowling {

  public static final int START_FRAME = 1;
  public static final int MAX_FRAME = 10;

  private String player;
  private List<Frame> frames = new ArrayList<>();
  private int current = START_FRAME;

  public Bowling(String player) {
    this.player = player;
    IntStream.range(0, MAX_FRAME).forEach(i -> frames.add(new Frame()));
  }

  public int size() {
    return frames.size();
  }

  public void play(int hitCount) {
    Frame frame = getCurrentFrame();
    frame.play(hitCount);
    if (frame.isEnd()) {
      this.current++;
    }
  }

  public Frame getCurrentFrame() {
    return frames.get(current - 1);
  }

  public Frame getFrame(int frameNumber) {
    int frameIndex = frameNumber - 1;
    return frames.get(frameIndex);
  }

  public int getCurrent() {
    return current;
  }

  public boolean hasBonus() {
    Frame frame = getFrame(MAX_FRAME);
    return frame.isSpare() || frame.isStrike();
  }

  public String getPlayer() {
    return player;
  }

  public List<Frame> getFrames() {
    return frames;
  }
}
