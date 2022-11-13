package bowling;

import bowling.domain.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.MultiFrames;
import bowling.domain.name.Name;
import bowling.domain.name.Names;

import static bowling.view.InputView.*;
import static bowling.view.ResultView.printMultiFrameResult;

public class BowlingApplication {
    public static void main(String[] args) {
        int countOfPlayer = inputCountOfPlayer();
        Names names = Names.init();
        MultiFrames multiFrames = MultiFrames.init(countOfPlayer);

        for (int i = 1; i <= countOfPlayer; i++) {
             names.addName(inputPlayerName(i));
        }
        printMultiFrameResult(names, multiFrames);

        while(!multiFrames.isOver()) {
            playBowlingGame(names, multiFrames);
        }
    }

    private static void playBowlingGame(Names names, MultiFrames multiFrames) {
        for (int i = 0; i < names.getCountOfPlayer(); i++) {
            doPlayerTurn(names, multiFrames, i);
        }
    }

    private static void doPlayerTurn(Names names, MultiFrames multiFrames, int i) {
        Frames frames = multiFrames.getPlayerFrame(i);
        Name name = names.getPlayerName(i);
        Frame frame = frames.getCurrentFrame();

        while (!frame.isFinished()) {
            Pin pin = inputCountOfPin(name);
            frame = frames.bowl(pin);
            printMultiFrameResult(names, multiFrames);
            multiFrames.addPlayerNextFrame(i, frame);
        }
    }
}
