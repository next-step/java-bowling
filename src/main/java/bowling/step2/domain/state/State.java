package bowling.step2.domain.state;

public interface State {
    String DELIMITER = "|";
    int COUNT_OF_MAX_PINS = 10;
    
    State bowl(int fallenPins);
    
    boolean isFinished();
    
    String display();
    
    boolean isSpare();
}
