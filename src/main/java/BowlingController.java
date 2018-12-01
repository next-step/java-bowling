import domain.NormalFrame;
import domain.Player;
import view.InputView;
import view.OutputView;

public class BowlingController {

    public static void main(String[] args) {
        Player player = InputView.getPlayer();

        NormalFrame normalFrame = NormalFrame.frameFactory(1);

        NormalFrame nextNormalFrame = normalFrame.first(InputView.getFrameThrow(normalFrame));
        OutputView.showBasicFrames(player, normalFrame);

    }
}
