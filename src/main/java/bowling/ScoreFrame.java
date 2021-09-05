package bowling;

public interface ScoreFrame {
    ScoreFrame process(int score);
    int getTurnNumber();
}
