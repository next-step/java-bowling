package bowling.domain;

public interface ScoreSheetReader {

    /**
     * @return 마지막 프레임까지 다 읽었으면 true, 그렇지 않으면 false
     */
    boolean isEOF();

    String readPlayName();

    FrameInfo readFrameInfo();
}
