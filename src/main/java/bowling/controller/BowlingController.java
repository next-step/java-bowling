package bowling.controller;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

	public void play() {
		InputView inputView = new InputView();
		OutputView outputView = new OutputView();

		Player player = new Player(inputView.getPlayer());

		Frames frames = new Frames();
		outputView.printFrame(player.getName(), frames);

		NormalFrame normalFrame = new NormalFrame();
		while (frames.isUpsertNormalFrame(normalFrame)) {
			frames.upsert(normalFrame);
			normalFrame = normalFrame.save(inputView.getScore(frames.size()));
			outputView.printFrame(player.getName(), frames);
		}

		FinalFrame finalFrame = new FinalFrame();
		while (frames.isUpsertFinalFrame(finalFrame)) {
			frames.upsert(finalFrame);
			finalFrame = finalFrame.save(inputView.getScore(frames.size()));
			outputView.printFrame(player.getName(), frames);
		}
	}
}
