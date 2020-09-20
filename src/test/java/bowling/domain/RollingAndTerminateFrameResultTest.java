package bowling.domain;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.frame.Frames;
import bowling.ui.result.DisplayPlayerBowlingGrade;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("모든 프레임에 같은 투구 후 마지막 결과 확인 테스트")
class RollingAndTerminateFrameResultTest {

    private Function<String, Integer> testPlayerFallenPinsService(final int fallenPins){
        return (s) -> fallenPins;
    }

    private static final Consumer<DisplayPlayerBowlingGrade> EMPTY_DISPLAY_PLAYER_BOWLING_GRADE_CONSUMER = (g) -> {};

    private static Player whileRollAndDisplay(Player player, int currentIndex, Function<String, Integer> playerFallenPinsService){
        player.whileRollAndDisplay(currentIndex, playerFallenPinsService, EMPTY_DISPLAY_PLAYER_BOWLING_GRADE_CONSUMER);
        return player;
    }

    @DisplayName("모든 투구가 실패한 경우")
    @Test
    void allGutter() {
        Function<String, Integer> gutter = testPlayerFallenPinsService(0);
        List<String> ksgGutterBowlingGrade = whileRollAndDisplay(Player.of("KSG"), Frames.MAX_FRAMES_SIZE, gutter)
                                                   .bowlingGrade();

        assertThat(ksgGutterBowlingGrade).last().isEqualTo("-");
    }

    @DisplayName("모두 미스 처리한 경우")
    @Test
    void allMiss() {
        Function<String, Integer> spare = testPlayerFallenPinsService(1);
        List<String> ksgSpareBowlingGrade = whileRollAndDisplay(Player.of("KSG"), Frames.MAX_FRAMES_SIZE, spare)
                                                  .bowlingGrade();

        assertThat(ksgSpareBowlingGrade).last().isEqualTo("1|1");
    }

    @DisplayName("모두 스페이어로 처리한 경우")
    @Test
    void allSpare() {
        Function<String, Integer> spare = testPlayerFallenPinsService(5);
        List<String> ksgSpareBowlingGrade = whileRollAndDisplay(Player.of("KSG"), Frames.MAX_FRAMES_SIZE, spare)
                                                  .bowlingGrade();

        assertThat(ksgSpareBowlingGrade).last().isEqualTo("5|/|5");
    }

    @DisplayName("모든 스트라이크로 처리한 경우")
    @Test
    void allStirke() {
        Function<String, Integer> stirke = testPlayerFallenPinsService(10);

        List<String> ksgStirkeBowlingGrade = whileRollAndDisplay(Player.of("KSG"), Frames.MAX_FRAMES_SIZE, stirke)
                                                   .bowlingGrade();

        assertThat(ksgStirkeBowlingGrade).last().isEqualTo("X|X|X");
    }
}