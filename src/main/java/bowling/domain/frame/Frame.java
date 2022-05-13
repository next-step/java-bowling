package bowling.domain.frame;

public interface Frame {

    boolean isFull();

    void addPin(int pinNo);

    Frame nextFrame(int pinNo);

    String toExpression();
}
