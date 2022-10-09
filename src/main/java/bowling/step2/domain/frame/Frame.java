package bowling.step2.domain.frame;

public interface Frame {
    Frame bowl(int fallenPins);
    boolean isNormalFrame();
    
    String display();
}
