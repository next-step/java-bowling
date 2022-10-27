package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @DisplayName("최종프레임 초기화는 볼링을 초기화하고 생성한다")
    @Test
    void init() {
        assertThat(FinalFrame.init()).isEqualTo(new FinalFrame(List.of(Bowling.init())));
    }

    @DisplayName("최종프레임 안의 투구들을 반환한다")
    @Test
    void getBowlings() {
        List<Bowling> result = finalFrame().getBowlings();

        List<Bowling> expected = bowlings();
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("투구하고 그 결과를 반환한다")
    @Test
    void bowl() {
        Frame result = finalFrame().bowl(FallenPins.of(2));

        Frame expected = new FinalFrame(bowlings()).bowl(FallenPins.of(2));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("첫번째 투구가 스트라이크고, 보너스 시도를 마쳤으면 최종프레임이 끝난다")
    @Test
    void finished_whenFirstBowlingIsStrikeAndBonusBowlingFinished() {
        Frame result = FinalFrame.init()
                .bowl(FallenPins.of(10))
                .bowl(FallenPins.of(9))
                .bowl(FallenPins.of(0));
        assertThat(result.isFinished()).isTrue();
    }

    @DisplayName("첫번째 투구가 스페어고, 보너스 시도를 마쳤으면 최종프레임이 끝난다")
    @Test
    void finished_whenFirstBowlingIsSpareAndBonusBowlingFinished() {
        Frame result = FinalFrame.init()
                .bowl(FallenPins.of(9))
                .bowl(FallenPins.of(1))
                .bowl(FallenPins.of(0));
        assertThat(result.isFinished()).isTrue();
    }

    @DisplayName("첫번째 투구가 스트라이크 또는 스페어가 아니면서 끝나면 최종프레임이 끝난다")
    @Test
    void finished_whenFirstBowlingIsFinishedBeingNotStrikeOrSpare() {
        Frame result = FinalFrame.init()
                .bowl(FallenPins.of(7))
                .bowl(FallenPins.of(2));
        assertThat(result.isFinished()).isTrue();
    }

    @DisplayName("첫번째 투구가 스트라이크이면 최종프레임은 끝나지 않는다.")
    @Test
    void notFinished_whenFirstBowlingIsStrike() {
        Frame result = FinalFrame.init()
                .bowl(FallenPins.of(10));
        assertThat(result.isFinished()).isFalse();
    }

    @DisplayName("첫번째 투구가 스트라이크이고 한 번더 시도했을 때 최종프레임은 끝나지 않는다.")
    @Test
    void notFinished_whenFirstBowlingIsStrikeAndTriesOneMore() {
        Frame result = FinalFrame.init()
                .bowl(FallenPins.of(10))
                .bowl(FallenPins.of(10));
        assertThat(result.isFinished()).isFalse();
    }

    @DisplayName("첫번째 투구가 스페어이면 최종프레임이 끝나지 않는다.")
    @Test
    void notFinished_whenFirstBowlingIsSpare() {
        Frame result = FinalFrame.init()
                .bowl(FallenPins.of(9))
                .bowl(FallenPins.of(1));
        assertThat(result.isFinished()).isFalse();
    }

    private static List<Bowling> bowlings() {
        return List.of(Bowling.init()
                .bowl(FallenPins.of(8)));
    }

    private Frame finalFrame() {
        return FinalFrame.init()
                .bowl(FallenPins.of(8));
    }
}
