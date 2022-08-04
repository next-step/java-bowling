package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.init();
    }

    @Test
    void init() {
        Frame firstFrame = new NormalFrame(1);
        assertThat(board).isEqualTo(new Board(List.of(firstFrame), firstFrame));
    }

    @DisplayName("쓰러트린 핀이 남은 핀을 넘으면 throw exp")
    @Test
    void handleAfterTry_fail() {
        board.handleAfterTry(3);
        assertThatThrownBy(() -> board.handleAfterTry(8))
                .isInstanceOf(BowlingException.class);
    }

    @DisplayName("투구가 끝나면 새로운 프레임은 보드에 추가된다.")
    @Test
    void moveToNextFrame() {
        assertThat(board.frameSize()).isEqualTo(1);
        board.handleAfterTry(3);
        board.calculateScore();
        board.handleAfterTry(4);
        board.calculateScore();
        assertThat(board.frameSize()).isEqualTo(2);
    }

    @DisplayName("투구가 안 끝나면 새로운 프레임은 보드에 추가되지 않는다.")
    @Test
    void notMoveToNextFrame() {
        assertThat(board.frameSize()).isEqualTo(1);
        board.handleAfterTry(3);
        assertThat(board.frameSize()).isEqualTo(1);
    }


    @DisplayName("인덱스에 해당하는 프레임을 찾는데 성공한다.")
    @Test
    void findPendingFrame() {
        board = new Board(List.of(new NormalFrame(1, 0, List.of(1, 9))));

        assertThat(board.search(1).getIndex()).isEqualTo(1);
    }

    @DisplayName("인덱스에 해당하는 프레임이 없어서 exp")
    @Test
    void findPendingFrame_fail() {
        board = new Board(Collections.emptyList());

        assertThatThrownBy(() -> board.search(1))
                .isInstanceOf(BowlingException.class);
    }

    @DisplayName("이전 프레임이 스페어일 때 투구 후 펜딩된 스페어 점수 계산한다.")
    @Test
    void calculateScore_success() {
        // given
        Frame current = new NormalFrame(2, 5, List.of(5), null, null, 0);
        Frame prev = new NormalFrame(1, 0, List.of(5,5), null, current, 0);

        List<Frame> frames = new ArrayList<>();
        frames.add(prev);
        frames.add(current);

        Queue<PendingFrame> queue = new LinkedList<>();
        queue.add(new PendingFrame(1, 1));

        board = new Board(frames, current, new ScorePendingQueue(queue));

        // when
        board.calculateScore();

        // then
        assertThat(prev.score()).isEqualTo(15);
    }

    @DisplayName("이전 프레임이 스트라이크이고, 현재 프레임도 투구가 끝났으면 펜딩된 스트라이크와 현재 프레임 모두 점수 계산한다.")
    @Test
    void calculateScore_success2() {
        // given
        board = Board.init();
        board.handleAfterTry(10);

        // when
        board.calculateScore();
        board.handleAfterTry(5);
        board.calculateScore();
        board.handleAfterTry(4);
        board.calculateScore();

        // then
        assertThat(board.search(1).score()).isEqualTo(19);
        assertThat(board.search(2).score()).isEqualTo(28);
    }
}

