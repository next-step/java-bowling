package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frameStatus.NormalFrameStatus;

import java.util.Arrays;
import java.util.Collections;

public class FakeDataForTest {
    public static final String PLAYER_NAME = "JBJ";

    public static final int TEN = 10;
    public static final int FIVE = 5;

    public static final NormalFrame FIVE_IN_PROGRESS_NORMAL_FRAME
            = new NormalFrame(1, NormalFrameStatus.bowlFirst(FIVE), null);
    public static final NormalFrame FIVE_FIVE_IN_PROGRESS_NORMAL_FRAME
            = new NormalFrame(1, NormalFrameStatus.bowlFirst(FIVE).bowl(FIVE), null);
    public static final NormalFrame STRIKE_FIRST_NORMAL_FRAME
            = new NormalFrame(1, NormalFrameStatus.bowlFirst(TEN), null);
    public static final NormalFrame STRIKE_SECOND_NORMAL_FRAME
            = new NormalFrame(2, NormalFrameStatus.bowlFirst(TEN), STRIKE_FIRST_NORMAL_FRAME);
    public static final NormalFrame STRIKE_THIRD_NORMAL_FRAME
            = new NormalFrame(3, NormalFrameStatus.bowlFirst(TEN), STRIKE_SECOND_NORMAL_FRAME);
    public static final NormalFrame STRIKE_FOURTH_NORMAL_FRAME
            = new NormalFrame(4, NormalFrameStatus.bowlFirst(TEN), STRIKE_THIRD_NORMAL_FRAME);
    public static final NormalFrame STRIKE_FIFTH_NORMAL_FRAME
            = new NormalFrame(5, NormalFrameStatus.bowlFirst(TEN), STRIKE_FOURTH_NORMAL_FRAME);
    public static final NormalFrame STRIKE_SIXTH_NORMAL_FRAME
            = new NormalFrame(6, NormalFrameStatus.bowlFirst(TEN), STRIKE_FIFTH_NORMAL_FRAME);
    public static final NormalFrame STRIKE_SEVENTH_NORMAL_FRAME
            = new NormalFrame(7, NormalFrameStatus.bowlFirst(TEN), STRIKE_SIXTH_NORMAL_FRAME);
    public static final NormalFrame STRIKE_EIGHTH_NORMAL_FRAME
            = new NormalFrame(8, NormalFrameStatus.bowlFirst(TEN), STRIKE_SEVENTH_NORMAL_FRAME);
    public static final NormalFrame STRIKE_NINTH_NORMAL_FRAME
            = new NormalFrame(9, NormalFrameStatus.bowlFirst(TEN), STRIKE_EIGHTH_NORMAL_FRAME);

    public static final FinalFrame FIRST_FIVE_FINAL_FRAME = FinalFrame.bowlFirst(FIVE, STRIKE_NINTH_NORMAL_FRAME);
    public static final FinalFrame FIVE_SPARE_FINAL_FRAME = FIRST_FIVE_FINAL_FRAME.bowl(FIVE);

    public static final FrameResults NORMAL_STRIKE_FRAME_RESULT
            = new FrameResults(Collections.singletonList(FrameResult.STRIKE));
    public static final FrameResults NORMAL_FIVE_IN_PROGRESS_FRAME_RESULT
            = new FrameResults(Collections.singletonList(FrameResult.FIVE));
    public static final FrameResults NORMAL_FIVE_SPARE_FRAME_RESULT
            = new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.SPARE));
}
