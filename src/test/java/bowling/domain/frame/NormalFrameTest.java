package bowling.domain.frame;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;
import bowling.domain.state.FirstBowling;
import bowling.domain.state.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {
    @DisplayName("일반프레임 초기화는 준비 상태로 생성된다")
    @Test
    void init() {
        assertThat(NormalFrame.init(1)).isEqualTo(new NormalFrame(1, new Ready()));
    }

    @DisplayName("투구 후 결과 프레임을 반환한다")
    @Test
    void bowl_returnThis() {
        Frame result = normalFrame();

        assertThat(result).isEqualTo(new NormalFrame(1, new FirstBowling(FallenPin.of(8))));
    }

    @DisplayName("투구 후 프레임이 끝나면 다음 번호의 일반프레임을 반환한다")
    @Test
    void bowl_returnNext() {
        Frame result = normalFrame().bowl(FallenPin.of(2));

        assertThat(result).isEqualTo(NormalFrame.init(2));
    }

    @DisplayName("9번째 프레임이 투구 후 끝나면 최종프레임을 반환한다")
    @Test
    void bowl_returnFinalFrame() {
        Frame frame = NormalFrame.init(9)
                .bowl(FallenPin.of(10));

        assertThat(frame).isEqualTo(FinalFrame.init());
    }

    @DisplayName("투구가 끝나면 일반프레임이 끝난다")
    @Test
    void finished_whenBowlingFinished() {
        Frame normalFrameWithMiss = normalFrame();
        Frame normalFrameWithSpare = normalFrame();
        Frame normalFrameWithStrike = NormalFrame.init(1);

        normalFrameWithMiss.bowl(FallenPin.of(1));
        normalFrameWithSpare.bowl(FallenPin.of(2));
        normalFrameWithStrike.bowl(FallenPin.of(10));

        assertAll(
                () -> assertThat(normalFrameWithMiss.isFinished()).isTrue(),
                () -> assertThat(normalFrameWithSpare.isFinished()).isTrue(),
                () -> assertThat(normalFrameWithStrike.isFinished()).isTrue());
    }

    @DisplayName("투구가 끝나지 않으면 일반프레임이 끝나지 않는다.")
    @Test
    void notFinished_whenBowlingNotFinished() {
        assertThat(normalFrame().isFinished()).isFalse();
    }

    @DisplayName("상태들을 반환한다")
    @Test
    void getStates() {
        assertThat(normalFrame().getStates()).isEqualTo(List.of(new FirstBowling(FallenPin.of(8))));
    }

    @DisplayName("준비상태인 프레임인지 여부를 반환한다")
    @Test
    void isReady() {
        assertAll(
                () -> assertThat(NormalFrame.init(1).isReady()).isTrue(),
                () -> assertThat(normalFrame().isReady()).isFalse());
    }

    @DisplayName("스코어를 반환한다")
    @Test
    void getScore() {
        Frame normalFrame = normalFrame();
        normalFrame.bowl(FallenPin.of(1));

        assertThat(normalFrame.getScore()).isEqualTo(new Score(9, 0));
    }

    @DisplayName("스코어를 계산할 수 없다면 null 인 스코어를 반환한다")
    @Test
    void getScore_returnNull_whenCannotCalculateScore() {
        Frame normalFrame = normalFrame();
        normalFrame.bowl(FallenPin.of(2));

        assertThat(normalFrame.getScore()).isNull();
    }

    @DisplayName("이전 스코어를 더한 결과를 반환한다")
    @Test
    void addScore() {
        Score previousScore = new Score(10, 1);
        Score result = normalFrame().addScore(previousScore);

        assertThat(result).isEqualTo(new Score(18, 0));
    }

    @DisplayName("스코어를 계산할 수 없다면 null 인 결과를 반환한다")
    @Test
    void addScore_returnNull_whenCannotCalculateScore() {
        Score previousScore = new Score(10, 2);
        Score result = normalFrame().addScore(previousScore);

        assertThat(result).isNull();
    }

    private Frame normalFrame() {
        return NormalFrame.init(1)
                .bowl(FallenPin.of(8));
    }
}
