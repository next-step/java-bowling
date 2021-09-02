package bowling.frame;

import bowling.dto.FrameDto;
import java.util.LinkedList;
import java.util.List;

public class Frames {

  private static final int FIRST_FRAME = 1;

  private final LinkedList<FrameDto> frames;

  public Frames() {
    this.frames = new LinkedList<>();
    frames.add(FrameDto.from(new NormalFrame(FIRST_FRAME)));
  }

  public void pitch(final int pin) {

    Frame currentFrame = frames.getLast().getFrame().play(pin);

    addNewFrame(currentFrame);
  }

  private void addNewFrame(final Frame currentFrame) {
    if (isNotSameFrame(currentFrame)) {
      frames.add(FrameDto.from(currentFrame));
    }
  }

  private boolean isNotSameFrame(final Frame currentFrame) {
    return !currentFrame.equals(frames.getLast().getFrame());
  }

  public int size() {
    return frames.size();
  }

  public List<FrameDto> resultList() {
    return frames;
  }

  public boolean isEnd() {
    return frames.getLast().getFrame().isGameEnd();
  }
}