package bowling.view;

import bowling.domain.Board;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Round;
import bowling.domain.turn.BallRelease;
import bowling.domain.turn.FallenPins;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
  private final static String SCORE_TITLE = "| NAME | 01 | 02 | 03 | 04 | 05 | 06 | 07 | 08 | 09 | 10 |";
  private final static String WALL = "|";
  private final static String BLANK = " ";
  private static final String NAME_FORMAT = "%6s";
  private static final String NUMBER_FORMAT = "%4s";


  public void printBoard(Round round){
    System.out.println();
    System.out.println(SCORE_TITLE);

    printName(round.playerName());
    for(Frame frame: round.frames()) {
      printFrame(frame);
    }
    System.out.println();
  }

  private void printFrame(Frame frame){
    List<FallenPins> shots = frame.ballReleases().stream().map(ballRelease -> ballRelease.fallenPins()).collect(Collectors.toList());

    PinsViewType viewType = PinsViewType.of(frame);
    StringBuilder stringBuilder = new StringBuilder();

    for(BallRelease ball : frame.ballReleases()){
      stringBuilder.append(String.format(viewType.printType(), ball.fallenPins().pins()));
    }
    System.out.print(String.format(NUMBER_FORMAT, stringBuilder.toString()));
    System.out.print(BLANK + WALL);
  }

  private void printName(String name){
    System.out.print(String.format(NAME_FORMAT, name));
    System.out.print(BLANK + WALL);
  }

}
