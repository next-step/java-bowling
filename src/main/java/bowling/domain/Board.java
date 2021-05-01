package bowling.domain;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

public class Board {
  private static final int FIRST_ROUND = 1;

  private final List<Frame> frames;

  public Board(){
    frames = new ArrayList<>();
  }

  public void makeFirstFrame(){
    frames.add(Frame.of(FIRST_ROUND));
  }

  public List<Frame> frames(){
    return frames;
  }

  public void addingFrame(){
    frames.add(tail().makeNextRound());
  }

  private Frame tail(){
    return frames.get(frames.size()-1);
  }

  public int size(){
    return frames.size();
  }
}
