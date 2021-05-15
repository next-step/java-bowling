package bowling.domain;

public interface Playable {
    void play(Point point);
    boolean isEnd();
}
