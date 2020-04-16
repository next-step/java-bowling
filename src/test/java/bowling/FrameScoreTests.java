package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("FrameScoreTests")
public class FrameScoreTests {

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void generateFrameScoreTest() {
        assertThatCode(FrameScore::new);
    }

    @DisplayName("FrameScore add 테스트")
    @Test
    public void addFrameScoreTest() {
        FrameScore frameScore = new FrameScore();
        assertThatCode(() -> frameScore.add(5));
        assertThatCode(() -> frameScore.add(4));
    }

    @DisplayName("FrameScore sum 테스트")
    @Test
    public void sumFrameScoreTest() {
        FrameScore frameScore = FrameScore.newInstance(Arrays.asList(1, 2));
        assertThat(frameScore.sum()).isEqualTo(3);
    }

    @DisplayName("FrameScore count 테스트")
    @Test
    public void countFrameScoreTest() {
        FrameScore frameScore = FrameScore.newInstance(Arrays.asList(1, 2));
        assertTrue(frameScore.isSameScoreCount(2));
    }

    @DisplayName("SubTotalScore 반환 테스트")
    @Test
    public void getSubTotalScoreTest() {
        FrameScore frameScore = FrameScore.newInstance(Arrays.asList(10));
        SubTotal expectedSubTotal = SubTotal.newInstance(10, NextAddingUpScores.newInstance(Arrays.asList(Score.of(10))));

        assertThat(frameScore.getSubTotal()).isEqualTo(expectedSubTotal);
    }

    @DisplayName("SubTotalScore 반환 테스트2")
    @Test
    public void getSubTotalScoreTest2() {
        FrameScore frameScore = FrameScore.newInstance(Arrays.asList(10));
        SubTotal subTotal = SubTotal.newInstance(10, NextAddingUpScores.newInstance(Arrays.asList(Score.of(10))));
        SubTotal expectedSubTotal = SubTotal.newInstance(20, NextAddingUpScores.newInstance(Arrays.asList(Score.of(10), Score.of(10))));

        assertThat(frameScore.getSubTotal(subTotal)).isEqualTo(expectedSubTotal);
    }

}
