package bowling.domain;

import bowling.domain.state.State;
import bowling.domain.state.StateFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

    Player player;
    Board board;

    @BeforeEach
    void setUp() {
        player = Player.of("asd");
        board = new Board(player.addName());
    }

    @Test
    void 생성_테스트() {
        System.out.println(board);
    }

    @Test
    void 점수판_미스() {
        State state = StateFactory.first(5);

        board.addFrame(1, state);

        if(!state.isFinished()) {
            System.out.println(" 프레임 투구2");
            state = state.nextPitch(3);
            board.addFrame(1, state);
        }

        System.out.println(board);
    }

    @Test
    void 점수판_스트라이크() {
        State state = StateFactory.first(10);

        board.addFrame(1, state);

        System.out.println(board);
    }

    @Test
    void 점수판_스페어() {
        State state = StateFactory.first(7);
        state = state.nextPitch(3);
        board.addFrame(1, state);

        System.out.println(board);
    }

    @Test
    void 점수판_거터() {
        State state = StateFactory.first(0);
        state = state.nextPitch(0);
        board.addFrame(1, state);

        System.out.println(board);
    }

    @Test
    void 마지막프레임_3스트라이크() {
        String result = "X|X|X|";
        board.addLast(10, result);
        System.out.println(board);
    }
}
