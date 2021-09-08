package bowling;

import bowling.frame.FramesResults;
import bowling.service.BowlingMatch;
import bowling.service.Player;

public class Main {


  public static void main(String[] args) {

    FramesResults frameResults = new FramesResults();

    Player.insertPlayer(frameResults);
    BowlingMatch.playMatch(frameResults);
  }
}