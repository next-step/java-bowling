package bowling;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("FrameScoreTests")
public class FrameScoreTests {

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void generateFrameScoreTest() {
        assertThatCode(FrameScore::new);
    }

    @DisplayName("FrameScore 첫번째 투구 추가 테스트")
    @Test
    public void addFirstPitchFrameScoreTest() {
        FrameScore frameScore = new FrameScore();
        assertThatCode(() -> frameScore.pitch(5));
    }

    @DisplayName("FrameScore 두번째 투구 추가 테스트")
    @Test
    public void addSecondPitchFrameScoreTest() {
        FrameScore frameScore = new FrameScore();
        frameScore.pitch(5);
        assertThatCode(() -> frameScore.pitch(4));
    }

    @DisplayName("FrameScore 세번째 투구 추가 테스트")
    @Test
    public void addThirdPitchFrameScoreTest() {
        FrameScore frameScore = new FrameScore();
        frameScore.pitch(5);
        frameScore.pitch(5);
        assertThatCode(() -> frameScore.pitch(4));
    }

    @DisplayName("FrameScore 합 테스트")
    @Test
    public void generateFrameScoreSumTest() {
        FrameScore frameScore = FrameScore.newInstance(Arrays.asList(5, 4));
        assertThat(frameScore.sum()).isEqualTo(9);
    }
}
