package bowling.frame;

import bowling.status.Status;

public class LastFrame implements Frame {

    private Status status;

    @Override
    public void shoot(ShootScore shootScore) { }

    @Override
    public boolean isEnd() {
        return status.isEnd();
    }
}
