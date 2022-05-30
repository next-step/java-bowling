package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingGameTest {
    @Test
    @DisplayName("볼링 게임 객체 생성에 대한 확인을 한다")
    void checkedBowlingGameObjectGenerate() {
        // given & when
        BowlingGame bowlingGame = new BowlingGame(new Player("ABC"));

        // then
        assertThat(bowlingGame).isNotNull();
    }

    @Test
    @DisplayName("플레이어가 null이거나 프레임이 null인 경우, 예외처리를 한다")
    void exceptoinPlayerIsNullOrFramesAreNull() {
        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> new BowlingGame(null, Frames.create()))
                        .isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new BowlingGame(new Player("ABC"), null))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("플레이어 이름을 확인한다")
    void checkedPlayerName() {
        // given
        Player player = new Player("ABC");

        // when
        BowlingGame bowlingGame = new BowlingGame(player);

        // then
        assertThat(bowlingGame.getPlayerName()).isEqualTo(player.toString());
    }

    @Test
    @DisplayName("다음 투구가 가능한지 확인한다")
    void hasNextPitching() {
        // given
        BowlingGame bowlingGame = new BowlingGame(new Player("ABC"));

        // when
        boolean result = bowlingGame.isNextPitching();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("현재 진행중인 프레임 번호를 확인한다")
    void checkedCurrentFrameNumber() {
        // given
        BowlingGame bowlingGame = new BowlingGame(new Player("ABC"));

        // when
        bowlingGame.bowl(new Pitching(10));

        // then
        assertThat(bowlingGame.getCurrentFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("현재 진행된 모든 프레임들의 갯수를 확인한다")
    void checkedAllProgressFrames() {
        // given
        BowlingGame bowlingGame = new BowlingGame(new Player("ABC"));

        // then
        assertThat(bowlingGame.getFrames()).hasSize(1);
    }

    @Test
    @DisplayName("현재 프레임이 진행이 가능한 프레임인지 확인한다")
    void checkedFrameProgress() {
        // given
        BowlingGame bowlingGame = new BowlingGame(new Player("AAA"));

        // when & then
        assertThat(bowlingGame.isFrameProgress(FrameNumber.first())).isTrue();
    }
}
