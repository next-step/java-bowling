package bowling.domain;

import java.util.List;

public interface Frame {
    List<Bowling> bowlings();

    Frame bowl(Score score);

    boolean isFinished();
}
