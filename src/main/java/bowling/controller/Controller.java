package bowling.controller;

import bowling.domain.NormalFrame;
import bowling.domain.Player;
import bowling.domain.interfaces.Frame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Controller {

    private Integer frameIdx = 1;
    private Player player;
    private Frame currentFrame;

    public void play() {
        Frame firstFrame = new NormalFrame(1);
        currentFrame = firstFrame;
        OutputView.showRecords(player, firstFrame);

        while (!currentFrame.isGameEnd()) {
            playFrame(firstFrame);
        }
    }

    private void playFrame(Frame firstFrame) {
        Frame frame = currentFrame.bowl(InputView.getScore(frameIdx));
        OutputView.showRecords(player, firstFrame);

        if (currentFrame != frame) {
            frameIdx++;
            currentFrame = frame;
        }
    }

    public void createPlayer() {
        this.player = new Player(InputView.getName());
    }
}
