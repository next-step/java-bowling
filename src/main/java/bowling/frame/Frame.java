package bowling.frame;

import bowling.status.Status;

public interface Frame {

    Frame shoot(ShootScore shootScore);

    boolean isEnd();

    Status findMyStatus();

}
