package bowling.service;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.presentation.input.ScoreInputView;
import bowling.presentation.output.FrameOutputView;

public class FramesService {

    private FramesService() {
    }

    public static FramesService create() {
        return new FramesService();
    }

    public Frames execute(Player player) {
        Frames frames = Frames.create();
        while (!frames.isLast()) {
            int score = ScoreInputView.create().input(frames.nextTryFrame());
            frames = frames.execute(score);
            FrameOutputView.create().print(player, frames);
        }
        return frames;
    }
}
