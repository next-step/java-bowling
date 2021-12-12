package bowling.domain.frame;

import bowling.domain.pin.Pin;

public interface Frame {

    /**
     * @return 이번 라운드에 더 투구할 수 있는지
     */
    boolean pitch(Pin pin);

    Frame next();

    boolean hasNextFrame();

    int getNumber();

}
