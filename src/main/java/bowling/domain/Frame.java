package bowling.domain;


public interface Frame {
    String COLUMN_WITH_FORMAT = "|%5s%2s";
    String BLANK = "   ";

    String printableTitle();
    String printableValue();
    void record();
    Frame makeNextFrame();
    boolean isFinalFrame();
}
