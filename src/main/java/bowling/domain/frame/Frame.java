package bowling.domain.frame;

public interface Frame {

    boolean isFull();

    void addPin(int pinNo);

    Frame getNextFrame(int pinNo);

    String toExpression();

    boolean canGetScore();

    int getScore();

    int spareBonusForPreviousFrame();

    int strikeBonusForPreviousFrame();
}
