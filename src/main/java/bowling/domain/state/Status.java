package bowling.domain.state;

import bowling.domain.Pin;

public interface Status {

    boolean isFinished();

    Status bowl(Pin pin);

}
