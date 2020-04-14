package bowling.domain;

public class Frame {
    private static final int MAX_FRAME_NO = 10;
    public static final String OVER_FRAME_NO_ERROR = "Frame은 최대 10개까지만 생성할 수 있습니다.";
    private int no;

    public Frame(int no) {
        assertFrameNo(no);
        this.no = no;
    }

    private void assertFrameNo(int no) {
        if(no > MAX_FRAME_NO) {
            throw new IllegalArgumentException(OVER_FRAME_NO_ERROR);
        }
    }
}
