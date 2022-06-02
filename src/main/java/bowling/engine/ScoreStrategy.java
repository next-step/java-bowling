package bowling.engine;

public interface ScoreStrategy {

    int createFirst();

    int createSecond(int prior);

}
