package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.turn.FallenPins;

import java.util.List;

public enum PinsViewType {
  STRIKE("X"),
  SPARE("%d|/"),
  NORMAL("%d|%d");

  private static final int MAX_NUMBER = 10;
  private static final int ZERO = 0;

  private final String symbol;

  PinsViewType(String symbol){
    this.symbol = symbol;
  }

  public static PinsViewType of(Frame frame){
    if(frame.isStrike()){
      return STRIKE;
    }

    if(frame.isSpare()){
      return SPARE;
    }

    return NORMAL;
  }

  public String printType(){
    return symbol;
  }
}
