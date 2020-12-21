package bowling.controller;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Controller {

    private Player player;
    private Frame currentFrame;

    public void play() {
        OutputView.showEmptyRecords(player);
        Frame firstFrame = new NormalFrame(1);
        currentFrame = firstFrame;

        while (!currentFrame.isGameEnd()) {
            playFrame(firstFrame);
        }
    }

    private void playFrame(Frame firstFrame) {
        currentFrame = currentFrame.bowl(InputView.getScore(currentFrame.getFrameCount()));
        OutputView.showRecords(player, firstFrame);
    }

    public void createPlayer() {
        this.player = new Player(InputView.getName());
    }
}
