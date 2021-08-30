package bowling.frame;

import java.util.LinkedList;
import java.util.List;

public class Frames {

  private final LinkedList<Frame> frames;

  public Frames() {
    this.frames = new LinkedList<>();
    frames.add(new NormalFrame());
  }

  public void pitch(final int pin) {

    Frame play = frames.getLast().play(pin, frames.size());
    if (!play.equals(frames.getLast())) {
      frames.add(play);
    }
  }

  public int size() {
    return frames.size();
  }

  public List<Frame> resultList(){
    return frames;
  }

  public boolean isEnd(){
    return frames.getLast().isGameEnd();
  }

}