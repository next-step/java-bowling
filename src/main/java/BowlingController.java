import domain.ResultManager;
import domain.enums.FrameNumberEnum;
import domain.frames.Frame;
import domain.frames.NormalFrame;
import domain.Player;
import view.InputView;
import view.OutputView;

public class BowlingController {

    public static void main(String[] args) {
        Player player = InputView.getPlayer();

        Frame normalFrame = NormalFrame.frameFactory(FrameNumberEnum.NEXT_DEFAULT_NUMBER.getFrameNumber());

        ResultManager resultManager = new ResultManager();
        while (true) {
            Frame nextNormalFrame = normalFrame.bowling(InputView.getFrameThrow(normalFrame));

            resultManager.addResult(normalFrame);

            if (!normalFrame.isSameFrame(nextNormalFrame) && !normalFrame.isStrike()) {
                resultManager.deleteLastResult();
            }

            OutputView.showBasicFrames(player, resultManager);
            normalFrame = nextNormalFrame;

            if (nextNormalFrame.getFrameNumber() == FrameNumberEnum.FINISH_FRAME_NUMBER.getFrameNumber()) {
                break;
            }
        }
    }
}
