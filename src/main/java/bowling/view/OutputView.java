package bowling.view;

import bowling.domain.Frames;

public interface OutputView {
    void questionPlayerName();
    void questionPinNumber(int frame);
    void resultsOfBowling(Frames frames);
}
