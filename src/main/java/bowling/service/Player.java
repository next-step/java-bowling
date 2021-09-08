package bowling.service;

import bowling.frame.FramesResult;
import bowling.frame.FramesResults;
import bowling.player.PlayerName;
import bowling.view.InsertView;
import java.util.stream.IntStream;

public class Player {

  private static final int PLAYER_MIN_COUNT = 1;

  public static void insertPlayer(final FramesResults frameResults) {
    IntStream.rangeClosed(PLAYER_MIN_COUNT, InsertView.playerCount())
        .mapToObj(i -> FramesResult.of(new PlayerName(InsertView.inputName(i))))
        .forEach(frameResults::addFrameResult);
  }
}
