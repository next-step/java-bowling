package bowling.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FrameScoreTest")
public class FrameScoreTest {

    private FrameScore frameScore = new FrameScore();

    @BeforeEach
    public void setUp() {
        frameScore = new FrameScore();
        frameScore.add(5);
        frameScore.add(4);
    }

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
        assertThat(frameScore.sum()).isEqualTo(9);
    }

    @DisplayName("FrameScore count 테스트")
    @Test
    public void countFrameScoreTest() {
        assertTrue(frameScore.isSameScoreCount(2));
    }
}