package bowling.domain;

import bowling.view.ResultView;

/**
 * Created : 2020-12-16 오전 9:30
 * Developer : Seo
 */
public class NormalFrame extends Frame {
    public static final int NEXT_FRAME = 1;
    public static final int LAST_FRAME = 10;

    private final int frameNo;
    private final Users users;

    public NormalFrame(Users users, int frameNo) {
        this.users = users;
        this.frameNo = frameNo;
    }

    @Override
    public Frame bowl(int frameNo) {
        users.bowl(frameNo);

        return this.frameNo + NEXT_FRAME == LAST_FRAME ?
                new FinalFrame() : new NormalFrame(users, this.frameNo + NEXT_FRAME);
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }
}
