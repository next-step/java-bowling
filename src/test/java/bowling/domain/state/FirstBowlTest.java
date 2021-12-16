package bowling.domain.state;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FirstBowlTest {

    @DisplayName("bowl() 시 두번의 합이 10이되지 않으면 miss반환")
    @Test
    void bowlToMiss() {
        FirstBowl firstBowl = new FirstBowl(Pin.from(5));
        assertThat(firstBowl.bowl(Pin.from(3))).isEqualTo(new Miss(Pin.from(5), Pin.from(3)));
    }

    @DisplayName("bowl() 시 두번의 합이 10이면 spare반환")
    @Test
    void bowlToSpare() {
        FirstBowl firstBowl = new FirstBowl(Pin.from(5));
        assertThat(firstBowl.bowl(Pin.from(5))).isEqualTo(new Spare(Pin.from(5), Pin.from(5)));
    }

    @DisplayName("FirstBowl viewString()은 Pin의 갯수를 반환한다.")
    @Test
    void viewStringTest() {
        assertThat(new FirstBowl(Pin.from(5)).viewString()).isEqualTo("5");
    }

    @DisplayName("score()은 NoScore를 반환한다.")
    @Test
    void scoreTest() {
        assertThat(new FirstBowl(Pin.from(5)).score()).isEqualTo(Score.noScore());
    }

    @DisplayName("calculateAdditionalScore() 핀의 갯수를 더해서 반환한다..")
    @Test
    void calculateAdditionalScoreTest() {
        assertThat(new FirstBowl(Pin.from(5)).calculateAdditionalScore(Score.of(5, 1)))
                .isEqualTo(Score.of(10, 0));
    }

    @DisplayName("pins() 는 pin값을 담은 리스트를 반환한다.")
    @Test
    void pinsTest() {
        assertThat(new FirstBowl(Pin.from(5)).pins()).isEqualTo(
                Arrays.asList(Pin.from(5))
        );
    }
}