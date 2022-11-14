package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {

    @Test
    void testIsStrike() {
        Scores scores = new Scores(maxNum -> 10);
        assertThat(scores.isStrike()).isTrue();
    }

    @Test
    void testNextScore(){
        Scores scores = new Scores(maxNum -> 5);
        scores.next();

        assertThat(scores).isEqualTo(new Scores(Arrays.asList(5,5)));
    }

}
