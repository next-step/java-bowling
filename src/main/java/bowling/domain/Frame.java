package bowling.domain;

public interface Frame {
    public static final String WRONG_FALLED_PIN = "넘어뜨린 핀의 개수가 알맞지 않습니다.";

    Frame play(int falledPin);

    State getStatus();

    Frame nextFrame();

    int getNo();
}
