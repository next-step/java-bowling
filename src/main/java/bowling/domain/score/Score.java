package bowling.domain.score;

import java.util.List;

public interface Score {

    Score saveNextPin(Pin pin);

    boolean isNextStorable();

    List<Pin> values();

}
