package bowling.domain.frame;

import java.util.Optional;

public interface Frame {
    Optional<Frame> getNext();
}
