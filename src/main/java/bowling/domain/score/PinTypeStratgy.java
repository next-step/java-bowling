package bowling.domain.score;

import java.util.List;

@FunctionalInterface
public interface PinTypeStratgy {

    boolean isScoreType(List<Pin> pins, int index);

}
