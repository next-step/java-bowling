package bowling.frame;

import bowling.dto.ScoreResultDto;
import bowling.score.ScoreResult;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

  private static final int FIRST_FRAME = 1;

  private final LinkedList<Frame> frames;

  public Frames() {
    this.frames = new LinkedList<>();
    frames.add(new NormalFrame(FIRST_FRAME));
  }

  public void pitch(final int pin) {

    Frame currentFrame = frames.getLast().play(pin);

    addNewFrame(currentFrame);
  }

  private void addNewFrame(final Frame currentFrame) {
    if (checkHaveNextFrame(currentFrame)) {
      frames.add(currentFrame);
    }
  }

  private boolean checkHaveNextFrame(final Frame currentFrame) {
    return !currentFrame.equals(frames.getLast());
  }

  public List<ScoreResultDto> totalScoreResult() {
    return getScoreResults()
        .stream()
        .map(ScoreResult::from)
        .collect(Collectors.toList());
  }

  private List<ScoreResult> getScoreResults() {
    return frames.getFirst().createScoreBoard().getScoreResults();
  }

  public boolean isEnd() {
    return frames.getLast().isGameEnd();
  }

  public boolean isFrameEnd() {
    return frames.getLast().isHaveNextFrame();
  }
}