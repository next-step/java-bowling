package bowling;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.domain.state.PinCount;
import bowling.view.InputView;
import bowling.view.ResultView;

public final class Bowling {

    private static final String RETRY_MESSAGE = " 다시 입력해주세요";

    private final static InputView INPUT_VIEW;
    private final static ResultView RESULT_VIEW;

    static {
        INPUT_VIEW = InputView.getInstance();
        RESULT_VIEW = ResultView.getInstance();
    }

    public static final void main(String[] args) {
        final Player player = getPlayer();
        final Frames frames = Frames.initialize();
        RESULT_VIEW.printScoreBoard(player, frames);
        while (!frames.isFinish()) {
            bowl(frames);
            RESULT_VIEW.printScoreBoard(player, frames);
        }
    }

    private static final void bowl(final Frames frames) {
        try {
            PinCount hitCount = PinCount.valueOf(INPUT_VIEW.InputHitCountByConsole(frames.index()));
            frames.bowl(hitCount);
        } catch (Exception e) {
            System.out.println(e.getMessage() + RETRY_MESSAGE);
            bowl(frames);
        }
    }


    private static final Player getPlayer() {
        try {
            return Player.from(INPUT_VIEW.InputPlayerNameByConsole());
        } catch (Exception e) {
            System.out.println(e.getMessage() + RETRY_MESSAGE);
            return getPlayer();
        }
    }

}
