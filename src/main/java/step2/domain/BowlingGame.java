package step2.domain;

public class BowlingGame {

    public static final int FRAME_FIRST_NO = 0;
    public static final int FRAME_LAST_NO = 9;
    public static final int FRAME_SIZE = 10;

    public static Frames build() {
        return Frames.Builder()
                .size(FRAME_SIZE)
                .head(NormalFrame.from(FRAME_FIRST_NO))
                .tail(new FinalFrame(FRAME_LAST_NO))
                .build();
    }
}
