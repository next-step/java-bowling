package bowling.domain.rollresult;

public interface RollResultType {
    int DEFAULT_MAX_SCORE = 10;
    int DEFAULT_MIN_SCORE = 0;
    boolean isStrike();
    boolean isSpare();
    boolean hasNext();
    int eval();
    RollResultType next(int nextScore);

}
