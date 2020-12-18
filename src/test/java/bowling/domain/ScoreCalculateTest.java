package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCalculateTest {
    @ParameterizedTest
    @MethodSource("strikeScoreTestPitchings")
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다. (2번의 투구가 스트라이크인 경우)")
    void strikeScoreTest(Integer pitching, Integer bonus1, Integer bonus2, Integer expectedScore) {
        Frames frames = Frames.init();

        frames.setKnockDownPins(KnockDownPins.valueOf(pitching));
        frames.setKnockDownPins(KnockDownPins.valueOf(bonus1));
        frames.setKnockDownPins(KnockDownPins.valueOf(bonus2));

        Integer frame1Score = frames.convertToDto().get(1).getTotalScore();
        assertThat(frame1Score).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> strikeScoreTestPitchings() {
        return Stream.of(
                Arguments.of(10, 10, 10, 30),
                Arguments.of(10, 10, 5, 25),
                Arguments.of(10, 5, 5, 20),
                Arguments.of(10, 3, 3, 16)
        );
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다.")
    void strikeScoreWithScoreTest() {
        Frames frames = Frames.init();

        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(3));
        frames.setKnockDownPins(KnockDownPins.valueOf(3));

        Integer frame1Score = frames.convertToDto().get(1).getTotalScore();
        assertThat(frame1Score).isEqualTo(16);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다. (2번째 투구가 스페어인 경우)")
    void strikeScoreWithSpareTest() {
        Frames frames = Frames.init();

        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));

        Integer frame1Score = frames.convertToDto().get(1).getTotalScore();
        assertThat(frame1Score).isEqualTo(20);
    }

    @Test
    @DisplayName("Spare 한 경우 다음번의 투구의 점수를 합한다.")
    void spareScoreTest() {
        Frames frames = Frames.init();

        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));

        Integer frame1Score = frames.convertToDto().get(1).getTotalScore();
        assertThat(frame1Score).isEqualTo(20);
    }

    @Test
    @DisplayName("Strike, Spare가 아닌 경우 현재 frame의 점수만 합산")
    void noneStrikeAndSpareTest() {
        Frames frames = Frames.init();

        frames.setKnockDownPins(KnockDownPins.valueOf(3));
        frames.setKnockDownPins(KnockDownPins.valueOf(3));

        Integer frame1Score = frames.convertToDto().get(1).getTotalScore();
        assertThat(frame1Score).isEqualTo(6);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다. (2번의 투구가 스트라이크인 경우)")
    void strikeScoreTest_lastFrame() {
        Frames frames = Frames.init();

        setUpToFrame8(frames);
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));

        Integer frame8Score = frames.convertToDto().get(8).getTotalScore();
        Integer frame9Score = frames.convertToDto().get(9).getTotalScore();
        assertThat(frame9Score - frame8Score).isEqualTo(30);
    }

    private void setUpToFrame8(Frames frames) {
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다.")
    void strikeScoreWithScoreTest_lastFrame() {
        Frames frames = Frames.init();

        setUpToFrame8(frames);
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(3));
        frames.setKnockDownPins(KnockDownPins.valueOf(3));

        Integer frame9Score = frames.convertToDto().get(9).getTotalScore();
        assertThat(frame9Score).isEqualTo(249);
    }

    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다. (2번째 투구가 스페어인 경우)")
    void strikeScoreWithSpareTest_lastFrame() {
        Frames frames = Frames.init();

        setUpToFrame8(frames);
        frames.setKnockDownPins(KnockDownPins.valueOf(10));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));

        Integer frame8Score = frames.convertToDto().get(8).getTotalScore();
        Integer frame9Score = frames.convertToDto().get(9).getTotalScore();
        assertThat(frame9Score - frame8Score).isEqualTo(20);
    }

    @Test
    @DisplayName("Spare 한 경우 다음번의 투구의 점수를 합한다.")
    void spareScoreTest_lastFrame() {
        Frames frames = Frames.init();

        setUpToFrame8(frames);
        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(5));
        frames.setKnockDownPins(KnockDownPins.valueOf(10));

        Integer frame8Score = frames.convertToDto().get(8).getTotalScore();
        Integer frame9Score = frames.convertToDto().get(9).getTotalScore();
        assertThat(frame9Score - frame8Score).isEqualTo(20);
    }
}
