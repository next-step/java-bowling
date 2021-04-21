package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

  private static final int FINAL_ROUND = 10;
  private final List<Frame> frames;
  private Round round;

  public Frames(List<Frame> frames) {
    this.frames = frames;
    this.round = Round.firstRound();
  }

  public static Frames init() {
    List<Frame> frames = normalFrames();
    frames.add(FinalFrame.of());
    return new Frames(frames);
  }

  private static List<Frame> normalFrames() {
    return IntStream.range(0, 9)
        .mapToObj(Round::new)
        .map(NormalFrame::new)
        .collect(Collectors.toList());
  }

  public void throwBall(int countOfHitPin) {
    Frame frame = frames.get(round() - 1);
    frame.play(countOfHitPin);
    if (frame.isEndFrame()) {
      round = round.next();
    }
  }

  public boolean isContinue() {
    return frames.get(FINAL_ROUND - 1).isLastFrame();
  }

  public int round() {
    return round.round();
  }

  public List<Frame> frames() {
    return Collections.unmodifiableList(frames);
  }
}
