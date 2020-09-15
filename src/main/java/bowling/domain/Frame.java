package bowling.domain;


public interface Frame {
    String COLUMN_WITH_FORMAT = "|%5s%3s";
    String BLANK = "   ";

    String printableTitle();
    String printableValue();
    boolean record(BowlingScore score);
    Frame makeNextFrame();
    boolean isFinalFrame();
    boolean isEnd();
}
