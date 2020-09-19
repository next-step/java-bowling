package bowling.view;

import bowling.domain.Participants;

public class ResultView {

    public void printResult(Participants participants) {
        PrintResult.printFrames(participants);
    }
}
