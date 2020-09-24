package bowling.step2.domain;

import bowling.step2.domain.frame.Frame;
import bowling.step2.domain.frame.NormalFrame;
import bowling.step2.view.InputView;
import bowling.step2.view.ResultView;

public class Game {
	private static final int FRAME_COUNT = 1;

	public Game() { }

	public void start() {
		Player player = new Player(InputView.inputPlayerName());
		ResultView.printInitBoard(player);

		Frame firstFrame = new NormalFrame(FRAME_COUNT);
		Frame currentFrame = firstFrame;
		while(!currentFrame.isGameOver()) {
			currentFrame = currentFrame.bowling(InputView.inputPinCount(currentFrame.getFrameNo()));
			ResultView.printEachFrame(player, firstFrame);
		}
	}

}
