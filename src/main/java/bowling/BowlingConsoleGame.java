package bowling;

import bowling.domain.player.BowlingPlayer;

public class BowlingConsoleGame {

    public static void main(String[] args) {
        BowlingPlayer bowlingPlayer = BowlingPlayer.from("name");

        while(bowlingPlayer.isBowlingEnd()) {

        }
    }

}
