package bowling;

import java.util.List;

public interface ScoreFrame {
    ScoreFrame process(int score);
    int getTurnNumber();
    String getScoreString();
}
