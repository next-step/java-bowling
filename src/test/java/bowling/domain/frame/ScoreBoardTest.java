package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreBoardTest {

    @Test
    @DisplayName("프레임이 종료 되면 새로운 프레임을 생성 테스트")
    void generate_bowl_test() {
        // given
        Frames frames = Frames.init();
        ScoreBoard scoreBoard = ScoreBoard.init(User.of("YKJ"), frames);

        // when
        scoreBoard.bowl(Pin.of(10));

        // then
        assertThat(frames.getLastIndex()).isEqualTo(Index.of(2));
    }

    @Test@DisplayName("모든 프레임이 종료 되면 게임이 종료되는 테스트")
    void isGameOver() {

        // given
        Frames frames = Frames.init();
        ScoreBoard board = ScoreBoard.init(User.of("YKJ"), frames);

        // when
        for (int i = 0; i < 12; i++) {
            board.bowl(Pin.of(10));
        }

        // then
        assertThat(board.isGameOver()).isTrue();
    }

}
