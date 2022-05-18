package bowling.frame;

import bowling.status.Status;

public interface Frame {

    void shoot(ShootScore shootScore);

    boolean isEnd();

    Status findMyStatus();

}
