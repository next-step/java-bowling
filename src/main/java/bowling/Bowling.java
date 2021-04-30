package bowling;

import bowling.domain.HitCount;
import bowling.domain.Player;
import bowling.view.InputView;

public class Bowling {

    private static final InputView INPUT_VIEW;

    static {
        INPUT_VIEW = InputView.getInstance();
    }

    public static void main(String[] args) {
        Player player = Player.from(INPUT_VIEW.InputUserNameByClient());
        for(int round=0; round < 10; round++) {
            playBowl(player, round);
        }

    }

    private static void playBowl(Player player, int round) {
        while (!player.isFinish(round)) {
            HitCount hitCount = HitCount.valueOf(INPUT_VIEW.InputHitCountByClient(round));
            player.bowl(round, hitCount);
        }
    }
}
