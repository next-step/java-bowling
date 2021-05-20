package bowling.domain.score;

public interface Score {

    int MINIMUM_SINGLE_SCORE = 0;
    int MAXIMUM_SINGLE_SCORE = 10;

    int INIT_SCORE = 0;
    boolean INIT_ENDED_SCORING = false;
    boolean ENDED_SCORING = true;

    void addScore(int score);

    void endScoring();

    boolean endedScoring();

    int score();
}
