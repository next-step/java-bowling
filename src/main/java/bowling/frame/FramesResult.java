package bowling.frame;

import bowling.dto.ResultDto;
import bowling.player.PlayerName;

public class FramesResult {

  private final PlayerName playerName;

  private final Frames frames;

  public FramesResult(final PlayerName playerName, final Frames frames) {
    this.playerName = playerName;
    this.frames = frames;
  }

  public static FramesResult of(final PlayerName playerName){
    return new FramesResult(playerName, new Frames());
  }

  public boolean isEndOfGame(){
    return frames.isEnd();
  }

  public void playing(final int pinCount){
    frames.pitch(pinCount);
  }

  public int round() {
    return frames.size();
  }

  public ResultDto getResult(){
    return ResultDto.of(playerName.toString(), frames.resultList());
  }
}