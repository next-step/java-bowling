package bowling.model;

public interface Frame {

  void roll(int KnockDownNumber) throws FrameOverException;

  Frame next();

  Score getScore();

  int getFirstKnockDownNumber();

  boolean isOver();

  boolean isFinished();

  FrameDTO createDTO();

}
