package bowling;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DisplayName("FrameScoreTests")
public class FrameScoreTests {

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void generateFrameScoreTest() {
        assertThatCode(CommonFrameScore::new);
        assertThatCode(LastFrameScore::new);
    }

    @DisplayName("FrameScore 첫번째 투구 추가 테스트")
    @Test
    public void addFirstPitchFrameScoreTest() {
        FrameScore frameScore = new CommonFrameScore();
        assertThatCode(() -> frameScore.pitch(5));
    }

    @DisplayName("FrameScore 두번째 투구 추가 테스트")
    @Test
    public void addSecondPitchFrameScoreTest() {
        FrameScore frameScore = new CommonFrameScore();
        frameScore.pitch(5);
        assertThatCode(() -> frameScore.pitch(4));
    }

    @DisplayName("FrameScore 두번째 투구 추가 오류 테스트")
    @ParameterizedTest
    @CsvSource(value = {"5,6", "10,4"})
    public void addSecondPitchFrameScoreAbnormalTest(final int firstPitch, final int secondPitch) {
        FrameScore frameScore = new CommonFrameScore();
        frameScore.pitch(firstPitch);
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> frameScore.pitch(secondPitch));
    }

    @DisplayName("FrameScore 세번째 투구 추가 테스트")
    @Test
    public void addThirdPitchFrameScoreTest() {
        FrameScore frameScore = new LastFrameScore();
        frameScore.pitch(5);
        frameScore.pitch(5);
        assertThatCode(() -> frameScore.pitch(4));
    }

    @DisplayName("FrameScore 세번째 투구 추가 오류 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10,3,8", "8,1,3"})
    public void addThirdPitchFrameScoreAbnormalTest(final int firstPitch, final int secondPitch, final int thirdPitch) {
        FrameScore frameScore = new LastFrameScore();
        frameScore.pitch(firstPitch);
        frameScore.pitch(secondPitch);
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> frameScore.pitch(thirdPitch));
    }

    @DisplayName("FrameScore 합 테스트")
    @Test
    public void generateFrameScoreSumTest() {
        FrameScore frameScore = CommonFrameScore.newInstance(Arrays.asList(5, 4));
        assertThat(frameScore.sum()).isEqualTo(9);
    }
}
