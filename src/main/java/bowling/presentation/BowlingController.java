package bowling.presentation;

import bowling.domain.Player;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.FinalFrames;
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
        FinalFrames finalFrames = FinalFrames.empty();

        printBowlingStart(player);
        executeNormalFrames(player, normalFrames, finalFrames);
        executeFinalFrames(player, normalFrames, finalFrames);

    }

    private void printBowlingStart(Player player) {
        FrameOutputView.create().print(player);
    }

    private void executeFinalFrames(Player player, NormalFrames normalFrames, FinalFrames finalFrames) {
        FinalFrame finalFrame = FinalFrame.start(ScoreInputView.create().inputLast());
        finalFrames.add(finalFrame);
        FrameOutputView.create().print(player, normalFrames, finalFrames);

        while (!finalFrame.isDone()) {
            finalFrame = finalFrame.next(ScoreInputView.create().inputLast());
            finalFrames.add(finalFrame);
            FrameOutputView.create().print(player, normalFrames, finalFrames);
        }
    }

    private void executeNormalFrames(Player player, NormalFrames normalFrames, FinalFrames finalFrames) {
        NormalFrame normalFrame = NormalFrame.start(ScoreInputView.create().inputStart());
        normalFrames.add(normalFrame);
        FrameOutputView.create().print(player, normalFrames, finalFrames);

        while (!normalFrame.isLast()) {
            normalFrame = normalFrame.next(ScoreInputView.create().input(normalFrame.nextFrame()));
            normalFrames.add(normalFrame);
            FrameOutputView.create().print(player, normalFrames, finalFrames);
        }
    }
}
