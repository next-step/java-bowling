package bowling.domain;

public interface Pins {
    String MAX_OVER_PINS = "넘어뜨리는 볼링핀은 10개가 최대입니다.";
    int MAX_PINS = 10;
    int FIRST_INDEX = 0;
    int MINUS_INDEX_ONE = 1;
    boolean IS_FIRST = true;
    boolean IS_NOT_FIRST = false;

    Pins first(int countOfDownPin);

    Pins next(int countOf);

    boolean isEnd();

    ScoreRule scoreRule();
}
