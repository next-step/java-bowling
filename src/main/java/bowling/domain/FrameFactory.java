package bowling.domain;

import bowling.view.FinalFrameResultView;
import bowling.view.NormalFrameResultView;
import bowling.view.ResultViewType;

public class FrameFactory {
    public static FrameType typeFactory(int frameNo, int maxFrameNo) {
        if (isFinalFrame(frameNo, maxFrameNo)) {
            return new FinalFrameType();
        }
        return new NormalFrameType();
    }

    public static ResultViewType viewFactory(int frameNo, int maxFrameNo) {
        if (isFinalFrame(frameNo, maxFrameNo)) {
            return new FinalFrameResultView();
        }
        return new NormalFrameResultView();
    }

    private static boolean isFinalFrame(int frameNo, int maxFrameNo) {
        return frameNo == maxFrameNo;
    }
}
