package bowling.domain.score;

public interface Calculator {

    Score getScore();

    boolean canAddNextScore();

    Calculator sumScore(Score score);
}
