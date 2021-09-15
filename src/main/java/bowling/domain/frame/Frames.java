package bowling.domain.frame;

import bowling.domain.Player;
import bowling.presentation.input.ScoreInputView;
import bowling.presentation.output.FrameOutputView;

import java.util.Objects;

public class Frames {

    private final NormalFrames normalFrames;

    private FinalFrame finalFrame;

    private Frames(NormalFrames normalFrames, FinalFrame finalFrame) {
        this.normalFrames = normalFrames;
        this.finalFrame = finalFrame;
    }

    public static Frames create() {
        return new Frames(NormalFrames.empty(), FinalFrame.init());
    }

    public void execute(Player player) {
        executeNormalFrames(player);
        finalFrame = executeFinalFrames(player);
    }

    private void executeNormalFrames(Player player) {
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

    private FinalFrame executeFinalFrames(Player player) {
        FinalFrame finalFrame = FinalFrame.init();
        finalFrame = tryFirst(player, normalFrames, finalFrame);
        finalFrame = trySecond(player, normalFrames, finalFrame);
        tryThird(player, normalFrames, finalFrame);
        return finalFrame;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames = (Frames) o;
        return Objects.equals(normalFrames, frames.normalFrames) && Objects.equals(finalFrame, frames.finalFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalFrames, finalFrame);
    }
}
