package bowling.domain.frame;

public interface Frame {

    void bowl(int score);

    boolean isEndCondition(int score);

    boolean isEnd();


}
