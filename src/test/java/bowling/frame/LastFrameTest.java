package bowling.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LastFrameTest {

    private static final int FOUR = 4;
    private static final int FIVE = 5;

    @Test
    void lastFrameCanShootAtLeastTwice() {
        LastFrame lastFrame = LastFrame.create();
        lastFrame.shoot(ShootScore.from(FIVE));
        lastFrame.shoot(ShootScore.from(FIVE));

        if (!lastFrame.isEnd()) {
            lastFrame.bonusShoot(ShootScore.from(FIVE));
        }

        assertThat(lastFrame.getThirdShoot().getShootScore()).isEqualTo(FIVE);
    }

    @Test
    void lastFrameNotShootBonus() {
        LastFrame lastFrame = LastFrame.create();

        lastFrame.shoot(ShootScore.from(FIVE));
        lastFrame.shoot(ShootScore.from(FOUR));

        if (!lastFrame.isEnd()) {
            lastFrame.bonusShoot(ShootScore.from(FIVE));
        }

        assertThat(lastFrame.getThirdShoot().getShootScore()).isEqualTo(0);
    }
}