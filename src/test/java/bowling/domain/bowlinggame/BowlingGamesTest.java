package bowling.domain.bowlinggame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class BowlingGamesTest {

    private BowlingGames createBowlingGames(List<String> playerNames) {
        return new BowlingGames(playerNames);
    }

    @Test
    @DisplayName("플레이어 이름이 없는 경우 Exception")
    void emptyPlayerNamesTest() {
        assertThatThrownBy(() -> createBowlingGames(Collections.EMPTY_LIST))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어 이름이 null인 경우 Exceptuon")
    void nullPlayerNamesTest() {
        assertThatThrownBy(() -> createBowlingGames(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("모든 볼링 게임에 frame 추가")
    void addNextFramesTest() {
        BowlingGames bowlingGames = createBowlingGames(Arrays.asList("PSJ", "KSY"));
        assertThatCode(() -> bowlingGames.addNextFrames());

        for (int i = 0; i < bowlingGames.size(); i++) {
            BowlingGame bowlingGame = bowlingGames.findBowlingGame(i);
            assertThat(bowlingGame.findCurrentFrame()).isNotNull();
        }
    }

    @ParameterizedTest
    @MethodSource("providePlayerNames")
    @DisplayName("player만큼 bowlingGame의 생성 테스트")
    void bowlingGameSizeTest(List<String> playerNames, int size) {
        BowlingGames bowlingGames = createBowlingGames(playerNames);
        assertThat(bowlingGames.size()).isEqualTo(size);
    }

    private static Stream<Arguments> providePlayerNames() {
        return Stream.of(
                Arguments.of(Arrays.asList("PSJ", "KSY"), 2),
                Arguments.of(Arrays.asList("PSJ"), 1),
                Arguments.of(Arrays.asList("PSJ", "KSY", "WIJ"), 3)
        );
    }

    @ParameterizedTest
    @MethodSource("provideGameOverScores")
    @DisplayName("모든 frame이 점수를 채웠을 경우 allGameOver 테스트")
    void allGameOverTest(List<Integer> points, Integer bonusScore) {
        List<String> playerNames = Arrays.asList("PSJ", "KSY");
        BowlingGames bowlingGames = createBowlingGames(playerNames);

        for (int i = 0; i < bowlingGames.size(); i++) {
            BowlingGame bowlingGame = bowlingGames.findBowlingGame(i);
            for (Integer point : points) {
                bowlingGame.addNextFrame();
                bowlingGame.writePoint(point);
            }
            bowlingGame.writePoint(bonusScore);
        }

        assertThat(bowlingGames.isAllGameOver()).isTrue();
    }

    private static Stream<Arguments> provideGameOverScores() {
        return Stream.of(
                Arguments.of(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10), 10)
        );
    }

    @Test
    @DisplayName("현재 bowlingGame이 play 가능한지 테스트")
    void isPlayableBowlingGameTest() {
        List<String> playerNames = Arrays.asList("PSJ", "KSY");
        BowlingGames bowlingGames = createBowlingGames(playerNames);
        bowlingGames.addNextFrames();

        BowlingGame firstBowlingGame = bowlingGames.findBowlingGame(0);
        firstBowlingGame.writePoint(10);

        assertThat(bowlingGames.isPlayable(0)).isFalse();
        assertThat(bowlingGames.isPlayable(1)).isTrue();
    }
}