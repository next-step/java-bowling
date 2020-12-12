package bowling.bowler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BowlersTest {

    private Bowlers bowlers;

    @Test
    @DisplayName("n명의 플레이어 입력")
    void createBowlers() {
        Bowler bowler1 = Bowler.of("lhg");
        Bowler bowler2 = Bowler.of("pjs");
        bowlers = Bowlers.from(Arrays.asList(bowler1, bowler2));
        assertThat(bowlers.getBowlers().get(0).getName()).isEqualTo("LHG");
        assertThat(bowlers.getBowlers().get(1).getName()).isEqualTo("PJS");
    }

}
