package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Preparation;
import bowling.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static bowling.domain.Fixture.DOWNED_PINS_10;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("프레임 하나에 대한 테스트")
class FrameTest {

    @DisplayName("프레임은 하나의 상태값을 가진다")
    @Test
    void init() {
        SomeFrame someFrame = new SomeFrame(Preparation.init());

        assertThat(someFrame).isInstanceOf(Frame.class);
    }

    @DisplayName("프레임이 가진 상태값이 EndState 일 경우 종료를 반환한다")
    @Test
    void isEnd() {
        SomeFrame someFrame = new SomeFrame(Preparation.init());

        assertThat(someFrame.isEnd()).isFalse();

        someFrame.downPins(DOWNED_PINS_10);

        assertThat(someFrame.isEnd()).isTrue();
    }

    @DisplayName("프레임은 기본적으로 볼링 게임이 끝난 상태가 아니다")
    @Test
    void isBowlingEnd() {
        SomeFrame someFrame = new SomeFrame(Preparation.init());

        assertThat(someFrame.isBowlingEnd()).isFalse();
    }

    static class SomeFrame extends Frame {
        protected SomeFrame(State state) {
            super(state);
        }

        @Override
        protected void appendFrame(List<Frame> frames) {}

        @Override
        public Score getScore() {
            return null;
        }

        @Override
        public Score addBonusScore(Score score) {
            return null;
        }
    }
}
