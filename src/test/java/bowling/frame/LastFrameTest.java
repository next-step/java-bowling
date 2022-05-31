package bowling.frame;

import org.junit.jupiter.api.Test;

import static bowling.frame.ConstShootScore.*;

class LastFrameTest {

    @Test
    void lastFrameTriple() {
        LastFrame lastFrame = LastFrame.create();
        lastFrame.shoot(STRIKE);
        lastFrame.shoot(STRIKE);
        lastFrame.shoot(STRIKE);

        lastFrame.findMyAllStatus()
                .forEach(System.out::println);
    }

    @Test
    void lastFrameOneStrikeAndLastTwoShoot() {
        LastFrame lastFrame = LastFrame.create();
        lastFrame.shoot(STRIKE);
        lastFrame.shoot(FIVE_SCORE);
        lastFrame.shoot(FIVE_SCORE);

        lastFrame.findMyAllStatus()
                .forEach(System.out::println);
    }

    @Test
    void lastFrameNotBonusScore() {
        LastFrame lastFrame = LastFrame.create();
        lastFrame.shoot(FIVE_SCORE);
        lastFrame.shoot(FOUR_SCORE);

        lastFrame.findMyAllStatus()
                .forEach(System.out::println);
    }
}