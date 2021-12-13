package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.pin.Pin;

import java.util.List;

public interface Frame {

    /**
     * @return 이번 라운드에 더 투구할 수 있는지
     */
    boolean pitch(Pin pin);

    Frame next();

    boolean hasNextFrame();

    int getNumber();

    List<Bowl> bowls();

}
