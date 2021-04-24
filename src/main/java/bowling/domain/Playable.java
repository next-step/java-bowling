package bowling.domain;

public interface Playable {

    void throwBall(int point);

    boolean ended();

    boolean striked();

    boolean spared();
}
