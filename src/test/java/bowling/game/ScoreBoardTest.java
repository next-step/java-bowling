package bowling.game;

import bowling.game.frame.Frames;
import bowling.player.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreBoardTest {

    @DisplayName("Player로 맞는 Frames를 찾는다.")
    @Test
    void findByPlayer() {
        Player player = new Player("lmh");
        ScoreBoard scoreBoard = new ScoreBoard(Arrays.asList(player));

        assertThat(scoreBoard.findByPlayer(player)).isInstanceOf(Frames.class);
    }

    @DisplayName("이름으로 맞는 Frames를 찾는다.")
    @Test
    void findByName() {
        Player player = new Player("lmh");
        ScoreBoard scoreBoard = new ScoreBoard(Arrays.asList(player));

        assertThat(scoreBoard.findByName("lmh")).isInstanceOf(Frames.class);
    }

    @DisplayName("없는 Player로 Frames를 찾으면 IllegalArgumentException throw")
    @Test
    void findByWrongPlayerThrowException() {
        Player player = new Player("lmh");
        ScoreBoard scoreBoard = new ScoreBoard(Arrays.asList(player));

        assertThatThrownBy(() -> scoreBoard.findByPlayer(new Player("cms")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지않는 플레이어 입니다.");
    }

    @DisplayName("없는 이름으로 Frames를 찾으면 IllegalArgumentException throw")
    @Test
    void findByWrongNameThrowException() {
        Player player = new Player("lmh");
        ScoreBoard scoreBoard = new ScoreBoard(Arrays.asList(player));

        assertThatThrownBy(() -> scoreBoard.findByName("cms"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지않는 플레이어 입니다.");
    }
}
