package bowling.domain;

public abstract class Frame {
    public static final int FRAME_INIT = 1;

    public static Frame init(Users users) {
        return new NormalFrame(users, FRAME_INIT);
    }

    public abstract Frame bowl(int frameNo);

    public abstract int getFrameNo();
}
