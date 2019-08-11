import bowling.domain.BowlingCenter;
import bowling.domain.Player;
import bowling.domain.Players;
import view.ConsoleInputView;
import view.ConsoleOutView;

import java.util.ArrayList;
import java.util.List;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-07 23:07
 */
public class BowlingApplication {
    public BowlingApplication() {
    }

    public static void main(final String[] args) {
        BowlingApplication app = new BowlingApplication();
        app.run();
    }

    public void run() {
        int playerCount = ConsoleInputView.inputPlayerCount();

        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            playerList.add(ConsoleInputView.inputPlayerName());
        }
        Players players = new Players(playerList);
        BowlingCenter center = new BowlingCenter(players);

        ConsoleOutView.printFrame(center, players);
        while (true) {
            int fallCount = ConsoleInputView.inputFallenBowl();
            if (!center.play(fallCount)) {
                break;
            }
            ConsoleOutView.printFrame(center, players);
        }
    }
}
