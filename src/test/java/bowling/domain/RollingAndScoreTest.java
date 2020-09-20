package bowling.domain;

import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.frame.Frames;
import bowling.ui.result.DisplayPlayerBowlingGrade;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("투구 후 점수 확인 테스트")
class RollingAndScoreTest {

    private Function<String, Integer> testPlayerFallenPinsService(final int fallenPins){
        return (s) -> fallenPins;
    }

    private static final Consumer<DisplayPlayerBowlingGrade> EMPTY_DISPLAY_PLAYER_BOWLING_GRADE_CONSUMER = (g) -> {};

    private static Player whileRollAndDisplay(Player player, Function<String, Integer> playerFallenPinsService){
        player.whileRollAndDisplay(Frames.MAX_FRAMES_SIZE, playerFallenPinsService, EMPTY_DISPLAY_PLAYER_BOWLING_GRADE_CONSUMER);
        return player;
    }

    private static Player whileRollAndDisplay(Player player, int currentIndex, Function<String, Integer> playerFallenPinsService){
        player.whileRollAndDisplay(currentIndex, playerFallenPinsService, EMPTY_DISPLAY_PLAYER_BOWLING_GRADE_CONSUMER);
        return player;
    }

    @DisplayName("모든 프레임이 거터인 경우 0점")
    @Test
    void gutter() {
        Function<String, Integer> gutter = testPlayerFallenPinsService(0);
        int totalFrameScore = whileRollAndDisplay(Player.of("KSG"), gutter)
                        .getTotalFrameScore();
        assertThat(totalFrameScore).isEqualTo(0);
    }

    @DisplayName("모든 프레임을 2번씩 투구 했을때 1점씩 나는 MISS인 경우 20점")
    @Test
    void allOnes() {
        Function<String, Integer> one = testPlayerFallenPinsService(1);
        int totalFrameScore = whileRollAndDisplay(Player.of("KSG"), one)
                                    .getTotalFrameScore();
        assertThat(totalFrameScore).isEqualTo(20);
    }

    @DisplayName("한번의 스페어 처리후 미스 인 경우")
    @Test
    void oneSpare() {
        int totalFrameScore = Player.of("KSG")
                                    .roll(5)
                                    .roll(5)
                                    .roll(3)
                                    .roll(0)
                                    .getTotalFrameScore();
        assertThat(totalFrameScore).isEqualTo(16);
    }

    @DisplayName("한번의 스트라이크 처리 후 미스 인 경우")
    @Test
    void oneStrike() {
        int totalFrameScore = Player.of("KSG")
                                    .roll(10)
                                    .roll(5)
                                    .roll(3)
                                    .getTotalFrameScore();
        assertThat(totalFrameScore).isEqualTo(26);
    }

    @DisplayName("모두 커터후 마지막 프레임 점수 확인")
    @Test
    void allGutterAndTerminalFrameScore() {
        Function<String, Integer> gutter = testPlayerFallenPinsService(0);
        final int FRAME_9 = 8;
        Player st1 =  whileRollAndDisplay(Player.of("AAA"), FRAME_9, gutter)
                           .roll(10)
                           .roll(5)
                           .roll(5);

        assertThat(st1.getTotalFrameScore()).isEqualTo(20);

        Player st2 = whileRollAndDisplay(Player.of("BBB"), FRAME_9, gutter)
                           .roll(3)
                           .roll(5);

        assertThat(st2.getTotalFrameScore()).isEqualTo(8);


        Player st3 = whileRollAndDisplay(Player.of("CCC"), FRAME_9, gutter)
                           .roll(5)
                           .roll(5)
                           .roll(5);
        assertThat(st3.getTotalFrameScore()).isEqualTo(15);
    }

    @DisplayName("퍼펙트 게임")
    @Test
    void perfectGame() {
        Function<String, Integer> stirke = testPlayerFallenPinsService(10);

        int totalFrameScore = whileRollAndDisplay(Player.of("KSG"), stirke)
                                    .getTotalFrameScore();
        assertThat(totalFrameScore).isEqualTo(300);
    }
}