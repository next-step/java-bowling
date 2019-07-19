package bowling.domain;

import java.util.List;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 03:20
 */
public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public int count() {
        return players.size();
    }
}
