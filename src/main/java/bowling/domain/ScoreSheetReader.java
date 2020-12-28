package bowling.domain;

public interface ScoreSheetReader {

    boolean isEOF();

    String readPlayName();

    FrameData readFrameData();
}
