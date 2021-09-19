package bowling.domain;

public interface Frame {

    void bowl(int hitPin);

    boolean isFinished();

    int findCurrentIndex();
    
    Frame next();

    Points currentFramePoints();

}
