package step4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step4.controller.BowlingController;
import step4.domain.Frames;
import step4.domain.NormalFrame;
import step4.domain.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FramePointTest {
    private Player player;

    @BeforeEach
    void setup() {
        player = Player.of("tst");
    }

    @DisplayName("올 스트라이크 점수 테스트")
    @ParameterizedTest
    @MethodSource("provideAllStrikeFrames")
    void allStrikePoint(Frames frames, int totalPoint, List<String> strings) {
        assertThat(frames.getTotalScores()).isEqualTo(totalPoint);

        assertThat(frames.getScores())
                .isEqualTo(strings);
    }

    private static Stream<Arguments> provideAllStrikeFrames() {
        Player player = new Player("Tst", Frames.Builder()
                .size(Player.FRAME_SIZE)
                .head(new NormalFrame(Player.FRAME_FIRST_NO))
                .build());
        while (!player.isFinished()) {
            player.pitches(10);
        }

        List<String> strings = Arrays.asList("30", "60", "90", "120", "150", "180", "210", "240", "270", "300");

        return Stream.of(
                Arguments.of(player.getFrames(), 300, strings)
        );
    }

    @DisplayName("볼링 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provideBowlingScoreInfo")
    void calculateBowlingScores(Frames frames, int totalScore, List<String> result) {
        assertThat(frames.getTotalScores()).isEqualTo(totalScore);
        assertThat(frames.getScores()).isEqualTo(result);

    }

    private static Stream<Arguments> provideBowlingScoreInfo() {
        Player player = Player.of("tst");
        List<Integer> pitchesCounts = Arrays.asList(1, 2, 3, 4, 5, 4, 5, 4, 3, 7, 10, 6, 4, 2, 8, 0, 2, 10, 5, 4);
        pitchesCounts.forEach(player::pitches);
        List<String> pitchesCountStrings = Arrays.asList("3", "10", "19", "28", "48", "68", "80", "90", "92", "111");

        return Stream.of(
                Arguments.of(player.getFrames(), 111, pitchesCountStrings)
        );
    }


}
