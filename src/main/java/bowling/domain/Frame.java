package bowling.domain;

public interface Frame {
    boolean isOver();
    void add(int value);
    Frame next();
    String display();
}
