package bowling.presentation;

import bowling.domain.Player;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.FinalFrames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.NormalFrames;

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

        FrameOutputView.create().print(player, normalFrames, finalFrames);

        NormalFrame normalFrame = NormalFrame.start(ScoreInputView.create().input(1));
        normalFrames.add(normalFrame);
        FrameOutputView.create().print(player, normalFrames, finalFrames);

        while (!normalFrame.isLast()) {
            normalFrame = normalFrame.next(ScoreInputView.create().input(normalFrame.nextTurnRound()));
            normalFrames.add(normalFrame);
            FrameOutputView.create().print(player, normalFrames, finalFrames);
        }


        FinalFrame finalFrame = FinalFrame.start(ScoreInputView.create().input(10));
        finalFrames.add(finalFrame);
        FrameOutputView.create().print(player, normalFrames, finalFrames);

        while (!finalFrame.isDone()) {
            finalFrame = finalFrame.next(ScoreInputView.create().input(10));
            finalFrames.add(finalFrame);
            FrameOutputView.create().print(player, normalFrames, finalFrames);
        }


        System.out.println();
    }
}
