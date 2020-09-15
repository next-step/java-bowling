package bowling.domain;


import bowling.constant.GameState;

public interface Frame {
    String COLUMN_WITH_FORMAT = "|%5s%3s";
    String BLANK = "   ";

    String printableTitle();
    String printableValue();
    GameState record(BowlingScore score);
    Frame makeNextFrame();
    boolean isFinalFrame();
}
