package bowling.presentation;

import bowling.domain.Player;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.NormalFrames;
import bowling.presentation.input.PlayerInputView;
import bowling.presentation.input.ScoreInputView;
import bowling.presentation.output.FrameOutputView;

public class BowlingController {

    private BowlingController() {
    }

    public static BowlingController create() {
        return new BowlingController();
    }

    public void execute() {

        Player player = Player.from(PlayerInputView.create().input());
        NormalFrames normalFrames = NormalFrames.empty();

        printBowlingStart(player);
        executeNormalFrames(player, normalFrames);
        executeFinalFrames(player, normalFrames);

    }

    private void printBowlingStart(Player player) {
        FrameOutputView.create().print(player);
    }

    private void executeNormalFrames(Player player, NormalFrames normalFrames) {
        NormalFrame normalFrame = NormalFrame.init();

        while (!normalFrame.isLast()) {

            normalFrame = normalFrame.tryFirst(ScoreInputView.create().input(normalFrame.nextIndex()));

            normalFrames.add(normalFrame);
            FrameOutputView.create().print(player, normalFrames);

            if (!normalFrame.isNowFrameDone()) {
                normalFrame.trySecond(ScoreInputView.create().input(normalFrame.nextIndex()));
                FrameOutputView.create().print(player, normalFrames);
            }
        }
    }

    private void executeFinalFrames(Player player, NormalFrames normalFrames) {

        FinalFrame finalFrame = FinalFrame.init();

        finalFrame = finalFrame.tryFirst(ScoreInputView.create().inputLastFrameScore());
        FrameOutputView.create().print(player, normalFrames, finalFrame);

        finalFrame = finalFrame.trySecond(ScoreInputView.create().inputLastFrameScore());
        FrameOutputView.create().print(player, normalFrames, finalFrame);

        if (!finalFrame.isLast()) {
            finalFrame = finalFrame.tryThird(ScoreInputView.create().inputLastFrameScore());
            FrameOutputView.create().print(player, normalFrames, finalFrame);
        }

    }
}
