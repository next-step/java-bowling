package bowling;

import bowling.domain.*;
import bowling.domain.state.State;
import bowling.ui.InputView;
import bowling.ui.ResultFrameDtos;
import bowling.ui.ResultView;

public class BowlingGame {

    public static void main(String[] args) {
        BowlingStrategy bowlingStrategy = new RandomBowlingStrategy();
        MultiUserFrames multiUserFrames = new MultiUserFrames();

        int playerCount = InputView.getPlayerCount();
        for (int i = 0; i < playerCount; i++) {
            Player player = new Player(InputView.getPlayerName());
            multiUserFrames.addPlayer(player);
        }

        ResultView.printBoard(new ResultFrameDtos(multiUserFrames));

        State state = State.ofNew();
        while (true) {
            Pin nextPin = bowlingStrategy.nextPin(state.getRemainPin());
            ResultView.printNextPin(multiUserFrames.getCurrentPlayerName(), nextPin);

            state = multiUserFrames.bowling(nextPin);
            ResultView.printBoard(new ResultFrameDtos(multiUserFrames));

            if (state.isFinish()) {
                break;
            }
        }
    }
}
