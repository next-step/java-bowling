import domain.ResultManager;
import domain.frames.NormalFrame;
import domain.Player;
import view.InputView;
import view.OutputView;

public class BowlingController {

    public static void main(String[] args) {
        Player player = InputView.getPlayer();


        NormalFrame normalFrame = NormalFrame.frameFactory(1);

        ResultManager resultManager = new ResultManager();
        while(true) {
            NormalFrame nextNormalFrame = normalFrame.bowling(InputView.getFrameThrow(normalFrame));
            resultManager.addResult(normalFrame);
            OutputView.showBasicFrames(player, resultManager);
            normalFrame = nextNormalFrame;
            if (normalFrame.getFrameNumber() == 9) {
                break;
            }
        }

    }
}
