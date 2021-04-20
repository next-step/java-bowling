package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

  private final List<NormalFrame> frames;
  private Round round;

  public Frames(List<NormalFrame> frames) {
    this.frames = frames;
    this.round = Round.firstRound();
  }

  public static Frames init() {
    List<NormalFrame> frames = normalFrames();
    return new Frames(frames);
  }

  private static List<NormalFrame> normalFrames() {
    return IntStream.range(0, 10)
        .mapToObj(i -> new NormalFrame())
        .collect(Collectors.toList());
  }

  public void throwBall(int countOfHitPin) {
    NormalFrame frame = frames.get(round() - 1);
    frame.play(countOfHitPin);

    if (frame.isEnd()) {
      round = round.next();
    }
  }

  public boolean isContinue() {
    return round() == 10;
  }

  public int round() {
    return round.round();
  }

  public List<NormalFrame> frames() {
    return Collections.unmodifiableList(frames);
  }
}
