package bowling;

import bowling.domain.Frames;
import bowling.domain.HitCount;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public final class Bowling {
    private final static InputView INPUT_VIEW;
    private final static ResultView RESULT_VIEW;

    static {
        INPUT_VIEW = InputView.getInstance();
        RESULT_VIEW = ResultView.getInstance();
    }

    public static void main(String[] args) {
        final Player player = getPlayer();
        Frames frames = Frames.initialize();
        RESULT_VIEW.printScoreBoard(player, frames);
        while (!frames.isFinish()) {
            HitCount hitCount = HitCount.valueOf(INPUT_VIEW.InputHitCountByConsole(frames.index()));
            frames.bowl(hitCount);
            RESULT_VIEW.printScoreBoard(player, frames);
        }
    }


    private static final Player getPlayer() {
        try {
            return Player.from(INPUT_VIEW.InputPlayerNameByConsole());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getPlayer();
        }
    }

}
