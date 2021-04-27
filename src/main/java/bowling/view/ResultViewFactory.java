package bowling.view;

public class ResultViewFactory {
    public static ResultViewType factory(int frameNo, int maxFrameNo) {
        if (frameNo == maxFrameNo) {
            return new FinalFrameResultView();
        }
        return new NormalFrameResultView();
    }
}
