package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-10 17:11
 */
public class GameBoardTest {
    @DisplayName("1~9의 점수 2개가 들어간뒤 투구를 한번 더했을때 List의 사이즈가 증가 (새로운 Frame)")
    @Test
    void play_new_frame() {
        GameBoard gameBoard = new GameBoard();
        gameBoard.play(5);
        gameBoard.play(5);
        gameBoard.play(5);
        assertThat(gameBoard.playCount()).isEqualTo(2);
    }

    @DisplayName("1~9의 점수 2개가 들어간뒤 투구를 한번 더했을때 List의 사이즈가 증가 (새로운 Frame)")
    @Test
    void play_snapshot() {
        GameBoard gameBoard = new GameBoard();
        gameBoard.play(1);
        gameBoard.play(2);
        Map<Integer, FrameScore> snapShot = new HashMap<>(gameBoard.play(3));

        assertThat(snapShot.get(1).getScores().get(0)).isEqualTo(Score.of(3));
    }
}
