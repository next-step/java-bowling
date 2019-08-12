package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-08-11 21:09
 */
class BowlingGameTest {

    @DisplayName("Player 이름 가지고 오기")
    @Test
    void 플레이어_이름_확인() {
        Player player = Player.of("KBY");
        BowlingGame game = BowlingGame.of(player);

        assertThat(game.getPlayerName()).isEqualTo(Player.of("KBY"));
    }

    @DisplayName("현재 진행중인 FrameNumber 확인")
    @Test
    void 프레임_번호_확인() {
        Player player = Player.of("KBY");
        BowlingGame game = BowlingGame.of(player);

        game.play(1);
        game.play(9);
        game.play(10);
        game.play(7);

        assertAll(
                () -> assertThat(game.getPlayerName()).isEqualTo(Player.of("KBY")),
                () -> assertThat(game.currentFrameNumber()).isEqualTo(3)
        );
    }

    @DisplayName("투구된 상태값 확인")
    @ParameterizedTest
    @CsvSource({
            "0,0,1,-|-",
            "3,7,1,3|/",
            "10,10,2,XX",
    })
    void 투구_상태_확인(int firstBowl, int secondBowl, int frameNumber, String status) {
        Player player = Player.of("KBY");
        BowlingGame game = BowlingGame.of(player);

        game.play(firstBowl);
        game.play(secondBowl);

        assertAll(
                () -> assertThat(game.getPlayerName()).isEqualTo(Player.of("KBY")),
                () -> assertThat(game.currentFrameNumber()).isEqualTo(frameNumber),
                () -> assertThat(game.displayState().stream().collect(Collectors.joining())).isEqualTo(status)
        );
    }

    @DisplayName("투구된 상태 점수 확인")
    @ParameterizedTest
    @CsvSource({
            "0,0,1,-|-,0",
            "3,7,1,3|/,10",
            "10,10,2,XX,30",
    })
    void 투구_점수_상태_확인(int firstBowl, int secondBowl, int frameNumber, String status, int score) {
        Player player = Player.of("KBY");
        BowlingGame game = BowlingGame.of(player);

        game.play(firstBowl);
        game.play(secondBowl);

        assertAll(
                () -> assertThat(game.getPlayerName()).isEqualTo(Player.of("KBY")),
                () -> assertThat(game.currentFrameNumber()).isEqualTo(frameNumber),
                () -> assertThat(game.displayState().stream().collect(Collectors.joining())).isEqualTo(status),
                () -> assertThat(game.displayScore().stream().mapToInt(Integer::intValue).sum()).isEqualTo(score)
        );
    }

    @DisplayName("현재 게임의 진행중인 프레임 번호가 맞는지 확인")
    @Test
    void 현재_프레임_확인() {
        Player player = Player.of("KBY");
        BowlingGame game = BowlingGame.of(player);

        game.play(10);
        game.play(10);
        game.play(10);

        assertAll(
                () -> assertThat(game.currentFrameNumber()).isEqualTo(3),
                () -> assertThat(game.matchFrameNumber(3)).isTrue()
        );
    }
}