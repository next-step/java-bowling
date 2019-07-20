import bowling.domain.BowlingCenter;
import bowling.domain.Player;
import bowling.domain.Players;
import view.ConsoleOutView;

import java.util.Arrays;
import java.util.Random;

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

    public static void main(final String[] args)
    {
        BowlingApplication app = new BowlingApplication();
        app.run();
    }

    public void run()
    {
        Players players = new Players(Arrays.asList(Player.of("KBY")));
        BowlingCenter center = new BowlingCenter(players);

        while(true) {
            if(!center.play()) {
                break;
            }
            ConsoleOutView.printFrameResult(center);
        }
    }
}
