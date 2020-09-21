package bowling.step2.domain;

import bowling.step2.domain.frame.Frame;
import bowling.step2.domain.frame.NormalFrame;
import bowling.step2.view.InputView;
import bowling.step2.view.ResultView;

public class Game {
	private static final int FRAME_COUNT = 1;

	private Player player;

	public Game() { }

	public void start() {
		player = new Player(InputView.inputPlayerName());
		ResultView.printInitBoard(player);

		Frame frame = new NormalFrame(FRAME_COUNT);
		while(!frame.isGameOver()) {
			// 현재 프레임의 다음 프레임 셋 해주는 방법 필요
			Frame prevFrame = frame;
			frame = prevFrame.bowling(InputView.inputPinCount(prevFrame.getFrameNo()));
			ResultView.printEachFrame(player, prevFrame);
		}
	}

}
