package bowling.domain;

import java.util.List;

public interface Pins {
    String MAX_OVER_PINS = "넘어뜨리는 볼링핀은 10개가 최대입니다.";
    int NORMAL_PINS_MAX_SIZE = 2;
    int MAX_PINS = 10;
    int FIRST_INDEX = 0;
    boolean IS_FIRST = true;

    Pins first(int countOfDownPin);

    Pins next(int countOf);

    boolean isEnd();

    ScoreRule scoreRule();

    List<Pin> pins();
}
