package bowling.domain.frame;

import bowling.domain.score.Pin;

public interface Frame {

    Frame bowling(Pin pin);

    Frame createNextFrame();

    int round();

}
