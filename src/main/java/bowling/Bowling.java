package bowling;

import bowling.domain.Frames;
import bowling.domain.HitCount;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public final class Bowling {
    private final static InputView INPUT_VIEW;
    private final static ResultView RESULT_VIEW;
    public static final String RETRY_MESSAGE = " 다시 입력해주세요";

    static {
        INPUT_VIEW = InputView.getInstance();
        RESULT_VIEW = ResultView.getInstance();
    }

    public static void main(String[] args) {
        final Player player = getPlayer();
        Frames frames = Frames.initialize();
        RESULT_VIEW.printScoreBoard(player, frames);
        while (!frames.isFinish()) {
            bowl(frames);
            RESULT_VIEW.printScoreBoard(player, frames);
        }
    }

    private static void bowl(Frames frames) {
        try {
            HitCount hitCount = HitCount.valueOf(INPUT_VIEW.InputHitCountByConsole(frames.index()));
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
