package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

  private final List<Frame> frames = new ArrayList<>();
  private static final int MIN_PLAY_COUNT = 1;
  private static final int MAX_PLAY_COUNT = 10;


  public List<Frame> getFrames() {
    return Collections.unmodifiableList(frames);
  }

  private boolean isFinalFrame(int index) {
    return frames.get(index) instanceof FinalFrame;
  }

  public boolean isEnd() {
    if (frames.size() == 0) {
      return false;
    }

    return isFinalFrame(frames.size() -1 ) && frames.get(frames.size() -1).isEnd();
  }


  public int getIndex() {
    if (frames.isEmpty() || getLastFrame().isEnd()) {
      return size() + 1;
    }
    return size();
  }

  public void play(int pinCount) {
    Frame frame = makeFrame();
    frame.play(new PinCount(pinCount));

    frames.add(frame);

    System.out.println("play test1 - state");
    System.out.println(getLastFrame().getState().getString());

    System.out.println("play test2");
    for (int i = 0; i< frames.size(); i++) {
      System.out.println(frames.get(i).getState().getString());
    }
  }


  private Frame makeFrame() {
    if (frames.isEmpty()) {
//      frames.add(NormalFrame.createFirst());
      return NormalFrame.createFirst();
    }

//    addFrames();
    return getLastFrame().next();
  }

  private void addFrames() {

    frames.add(getLastFrame().next());
  }

  private Frame getLastFrame() {
    return frames.get(size() - 1);
  }

  public int size() {
    return frames.size();
  }



}
