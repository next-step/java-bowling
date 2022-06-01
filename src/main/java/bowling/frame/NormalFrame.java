package bowling.frame;

import bowling.status.Ready;
import bowling.status.Status;

public class NormalFrame implements Frame {

    private Status status;

    private NormalFrame() {
        status = Ready.create();
    }

    public static NormalFrame create() {
        return new NormalFrame();
    }

    @Override
    public void shoot(ShootScore shootScore) {
        if (isEnd()) {
            throw new IllegalArgumentException("종료된 프레임에서는 더 투구할 수 없습니다.");
        }
        status = status.shoot(shootScore);
    }

    @Override
    public boolean isEnd() {
        return status.isEnd();
    }

    @Override
    public Status findMyStatus() {
        return status;
    }
}
