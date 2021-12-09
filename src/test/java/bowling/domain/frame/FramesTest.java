package bowling.domain.frame;

import bowling.domain.state.FinalFrameState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("생성 테스트")
    @Test
    void createTest() {
        LinkedList<Frame> frames = new LinkedList<>();
        frames.add(NormalFrame.readyFrame(Round.from(1)));
        assertThat(Frames.from(frames)).isEqualTo(Frames.from(frames));
    }

    @DisplayName("마지막 frame의 isGameEnd를 반환한다.")
    @Test
    void isEndGameTest() {
        LinkedList<Frame> frames = new LinkedList<>();
        NormalFrame first = NormalFrame.readyFrame(Round.from(1));
        frames.add(first);
        assertThat(Frames.from(frames).isGameEnd()).isFalse();

        FinalFrame last = FinalFrame.of(FinalFrameState.of(Collections.EMPTY_LIST, 0));
        frames.add(last);
        assertThat(Frames.from(frames).isGameEnd()).isTrue();
    }

    @DisplayName("add() 프레임을 추가한다.")
    @Test
    void addTest() {

    }
}
