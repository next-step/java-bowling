package bowling.domain;

import bowling.view.ResultView;

import java.util.List;

/**
 * Created : 2020-12-16 오전 9:30
 * Developer : Seo
 */
public class NormalFrame extends Frame {
    public static final String FORMATTER = "0";
    public static final int NEXT_FRAME = 1;
    public static final int LAST_FRAME = 10;

    private final int frameNo;
    private Users users;

    public NormalFrame(Users users, int frameNo) {
        this.users = users;
        this.frameNo = frameNo;
    }

    @Override
    public Frame bowl(ResultView resultView) {
        users.bowl(resultView);

        return this.frameNo + NEXT_FRAME == LAST_FRAME ?
                new FinalFrame() : new NormalFrame(users, this.frameNo + NEXT_FRAME);
    }

    @Override
    public Users getUsers() {
        return this.users;
    }
}
