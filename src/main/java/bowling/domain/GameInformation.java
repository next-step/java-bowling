package bowling.domain;

import bowling.domain.frame.Frame;

public class GameInformation {

  private final Player player;
  private final FrameResult frameResult;

  public GameInformation(String  name) {
    this(new Player(name), new FrameResult());
  }
  public GameInformation(Player player, FrameResult frameResult) {
    this.player = player;
    this.frameResult = frameResult;
  }

  public FrameResult getFrameResult() {
    return frameResult;
  }

  public void addFrameResult(Frame frame) {
    frameResult.add(frame.getPlayCount(), frame.getState());
  }

  public Player getPlayer() {
    return player;
  }
}
