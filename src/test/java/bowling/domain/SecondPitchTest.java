package bowling.domain;

import bowling.domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SecondPitchTest {

    private SecondPitch secondPitch;

    @BeforeEach
    void setUp() {
        List<Integer> falls = new ArrayList<>();
        falls.add(5);
        secondPitch = new SecondPitch(new Pins(5, falls));
    }

    @Test
    @DisplayName("두번째 볼링 결과 - miss")
    void bowl_miss() {
        State miss = secondPitch.bowl(4);

        assertThat(miss).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("두번째 볼링 결과 - 스페어")
    void bowl_spare() {
        State miss = secondPitch.bowl(5);

        assertThat(miss).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("다음 스코어 더하기")
    void addNextScore() {
        Score score = new Score();

        Score nextScore = secondPitch.addNextScore(score);

        assertThat(nextScore).isEqualTo(new Score(5, 1));
    }
}
