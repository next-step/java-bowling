package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    @Test
    void testIsFull(){
        Scores scoresA = new Scores();
        scoresA.add(1);
        scoresA.add(2);
        assertThat(scoresA.isFull()).isTrue();

        Scores scoresB = new Scores();
        scoresB.add(10);
        assertThat(scoresB.isFull()).isTrue();
    }

}
