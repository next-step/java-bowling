package bowling.domain;

public interface Frame {
    Frame bowl(int pins);
    Score calculateScore(Score beforeScore);
    int score();
    String frameExpression();
}
