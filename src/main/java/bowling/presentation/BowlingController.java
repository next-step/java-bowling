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
            normalFrame = tryFirst(player, normalFrames, normalFrame);
            trySecond(player, normalFrames, normalFrame);
        }
    }

    private NormalFrame tryFirst(Player player, NormalFrames normalFrames, NormalFrame normalFrame) {
        normalFrame = normalFrame.tryFirst(ScoreInputView.create().input(normalFrame.nextIndex()));
        normalFrames.add(normalFrame);
        FrameOutputView.create().print(player, normalFrames);
        return normalFrame;
    }

    private void trySecond(Player player, NormalFrames normalFrames, NormalFrame normalFrame) {
        if (!normalFrame.isNowFrameDone()) {
            normalFrame.trySecond(ScoreInputView.create().input(normalFrame.nextIndex()));
            FrameOutputView.create().print(player, normalFrames);
        }
    }

    private void executeFinalFrames(Player player, NormalFrames normalFrames) {

        FinalFrame finalFrame = FinalFrame.init();
        finalFrame = tryFirst(player, normalFrames, finalFrame);
        finalFrame = trySecond(player, normalFrames, finalFrame);
        tryThird(player, normalFrames, finalFrame);

    }

    private FinalFrame tryFirst(Player player, NormalFrames normalFrames, FinalFrame finalFrame) {
        finalFrame = finalFrame.tryFirst(ScoreInputView.create().inputLastFrameScore());
        FrameOutputView.create().print(player, normalFrames, finalFrame);
        return finalFrame;
    }

    private FinalFrame trySecond(Player player, NormalFrames normalFrames, FinalFrame finalFrame) {
        finalFrame = finalFrame.trySecond(ScoreInputView.create().inputLastFrameScore());
        FrameOutputView.create().print(player, normalFrames, finalFrame);
        return finalFrame;
    }

    private void tryThird(Player player, NormalFrames normalFrames, FinalFrame finalFrame) {
        if (!finalFrame.isLast()) {
            finalFrame = finalFrame.tryThird(ScoreInputView.create().inputLastFrameScore());
            FrameOutputView.create().print(player, normalFrames, finalFrame);
        }
    }
}
