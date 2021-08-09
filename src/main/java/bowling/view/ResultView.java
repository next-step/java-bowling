package bowling.view;

import bowling.domain.ScoreBoard;

public interface ResultView {
    void printScoreBoard(ScoreBoard scoreBoard);

    void printException(Exception e);
}
