package bowling.domain.frame;

import bowling.domain.score.SpareScoreStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    void fallenPinsGiven_subtractPins() {
        Frame frame = new NormalFrame();
        frame.handleAfterTry(4);

        assertThat(frame).isEqualTo(new NormalFrame(6, List.of(4)));
    }

    @DisplayName("남은 핀이 없으면 다음 프레임으로 넘어간다.")
    @Test
    void askFrameIsOver() {
        Frame frame = new NormalFrame(1, 0, List.of(3, 7));
        assertThat(frame.moveToNextFrame()).isEqualTo(new NormalFrame(2));
    }

    @DisplayName("남은 핀이 있어도 시도횟수가 0이면 다음 프레임으로 넘어간다.")
    @Test
    void askFrameIsOver3() {
        Frame normalFrame = new NormalFrame(1);
        normalFrame.handleAfterTry(4);
        assertThat(normalFrame.moveToNextFrame()).isEqualTo(normalFrame);
    }

    @DisplayName("프레임에게 투구를 다 던졌는지 물어보고 다 안 던졌으면 자기 자신 리턴한다.")
    @Test
    void askFrameIsOver2() {
        Frame frame = new NormalFrame(1, 4, List.of(6));
        assertThat(frame.moveToNextFrame()).isEqualTo(frame);
    }

    @DisplayName("프레임이 투구를 다 던지면 다음 프레임 생성하고 현재 프레임과 연결해준다.")
    @Test
    void link() {
        // given
        Frame currentFrame = new NormalFrame(1, 0, List.of(3, 7));
        Frame nextFrame = new NormalFrame(2, currentFrame, null);

        // when, then
        assertThat(currentFrame.moveToNextFrame()).isEqualTo(nextFrame);
        assertThat(currentFrame.getNext()).isEqualTo(nextFrame);
        assertThat(nextFrame.getPrev()).isEqualTo(currentFrame);
    }

    @DisplayName("9번째 프레임이 다 던지면 파이널 프레임을 생성한다.")
    @Test
    void createFinalFrame() {
        // given
        Frame frame = new NormalFrame(9, 0, List.of(3,7));

        // when, then
        Frame finalFrame = new FinalFrame(10, frame, null);
        assertThat(frame.moveToNextFrame()).isEqualTo(finalFrame);
        assertThat(finalFrame.getPrev()).isEqualTo(frame);
    }

    @DisplayName("프레임의 스코어 타입을 정한다.")
    @Test
    void scoreType() {
        // given
        NormalFrame commonFrame = new NormalFrame(1, 5, List.of(1, 4));
        NormalFrame spareFrame = new NormalFrame(2, 0, List.of(1, 9));
        NormalFrame strikeFrame = new NormalFrame(3, 0, List.of(10));

        // when ,then
        assertThat(commonFrame.scoreType()).isEqualTo(ScoreType.COMMON);
        assertThat(spareFrame.scoreType()).isEqualTo(ScoreType.SPARE);
        assertThat(strikeFrame.scoreType()).isEqualTo(ScoreType.STRIKE);
    }

    @DisplayName("점수 전략을 전달받고 점수 계산에 성공한다.")
    @Test
    void computeScore() {
        // given
        NormalFrame prev = new NormalFrame(2, 0, List.of(3, 4), null, null, 90);
        NormalFrame next = new NormalFrame(4, 6, List.of(4), null, null, 0);
        Frame current = new NormalFrame(3, 0, List.of(5, 5), prev, next, 0);

        // when
        current.computeScore(new SpareScoreStrategy());

        // then
        assertThat(current.score()).isEqualTo(104);
    }
}
