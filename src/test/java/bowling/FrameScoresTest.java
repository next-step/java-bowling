package bowling;

import bowling.domain.FrameScores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FrameScoresTest {
    @Test
    @ParameterizedTest
    public void frameScoreSingleTest() {
        FrameScores frameScores = new FrameScores(Arrays.asList(7));
        assertThat(frameScores.getScore().get(0)).isEqualTo("7");
    }

    @Test
    public void frameScoreMultiTest() {
        FrameScores frameScores = new FrameScores(Arrays.asList(8, 16, 23));
        assertThat(frameScores.getScore().get(1)).isEqualTo("16");
    }
}
