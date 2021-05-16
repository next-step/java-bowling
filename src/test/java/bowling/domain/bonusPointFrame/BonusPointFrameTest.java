package bowling.domain.bonusPointFrame;

import bowling.domain.frame.NormalFrame;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusPointFrameTest {

    private static final int STRIKE_BONUS = 2;
    private static final int SPARE_BONUS = 1;

    @Test
    @DisplayName("2개의 보너스포인트가 있는 경우 두번 점수를 더해줄 수 있다")
    void canDetermineTwoBonusPointFrame() {
        SoftAssertions softAssertions = new SoftAssertions();

        BonusPointFrame bonusPointFrame = new BonusPointFrame(STRIKE_BONUS, new NormalFrame());
        softAssertions.assertThat(bonusPointFrame.needMoreBonus()).isTrue();

        bonusPointFrame.addBonusPoint(STRIKE_BONUS);
        softAssertions.assertThat(bonusPointFrame.needMoreBonus()).isTrue();

        bonusPointFrame.addBonusPoint(STRIKE_BONUS);
        softAssertions.assertThat(bonusPointFrame.needMoreBonus()).isFalse();

        softAssertions.assertAll();
    }

    @Test
    @DisplayName("1개의 보너스포인트가 있는 경우 한번 점수를 더해줄 수 있다")
    void canDetermineOneBonusPointFrame() {
        SoftAssertions softAssertions = new SoftAssertions();

        BonusPointFrame bonusPointFrame = new BonusPointFrame(SPARE_BONUS, new NormalFrame());
        softAssertions.assertThat(bonusPointFrame.needMoreBonus()).isTrue();

        bonusPointFrame.addBonusPoint(SPARE_BONUS);
        softAssertions.assertThat(bonusPointFrame.needMoreBonus()).isFalse();

        softAssertions.assertAll();
    }

}
