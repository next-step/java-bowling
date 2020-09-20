package bowling;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BowlingPinTest {

    @Test
    void createTest(){
        BowlingPin bowlingPin = BowlingPin.getInitialPin();

        bowlingPin.roll(10);

        assertThat(bowlingPin.isAllFallen()).isEqualTo(true);
    }

    @Test
    void exceedMaxTest(){
        BowlingPin bowlingPin = BowlingPin.getInitialPin();

        assertThatIllegalArgumentException().isThrownBy(()-> bowlingPin.roll(11))
                .withMessage("exceed max pin Num");
    }
}