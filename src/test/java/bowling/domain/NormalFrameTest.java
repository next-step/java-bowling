package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {

    @DisplayName("일반프레임 초기화는 볼링을 초기화하고 생성한다")
    @Test
    void init() {
        assertThat(NormalFrame.init()).isEqualTo(new NormalFrame(Bowling.init()));
    }

    @DisplayName("일반프레임 안의 투구들을 반환한다")
    @Test
    void bowlings() {
        List<Bowling> result = normalFrame().bowlings();

        List<Bowling> expected = List.of(bowling());
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("투구하고 그 결과를 반환한다")
    @Test
    void bowl() {
        Frame result = normalFrame().bowl(new Score(2));

        Frame expected = new NormalFrame(bowling().bowl(new Score(2)));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("투구가 끝나면 일반프레임이 끝난다")
    @Test
    void finished_whenBowlingFinished() {
        Frame normalFrameWithTriesDoneBowling = normalFrame().bowl(new Score(1));
        Frame normalFrameWithStrikeBowling = NormalFrame.init().bowl(new Score(10));

        assertAll(
                () -> assertThat(normalFrameWithTriesDoneBowling.isFinished()).isTrue(),
                () -> assertThat(normalFrameWithStrikeBowling.isFinished()).isTrue());
    }

    @DisplayName("투구가 끝나지 않으면 일반프레임이 끝나지 않는다.")
    @Test
    void notFinished_whenBowlingNotFinished() {
        assertThat(normalFrame().isFinished()).isFalse();
    }

    private static Bowling bowling() {
        return Bowling.init()
                .bowl(new Score(8));
    }

    private Frame normalFrame() {
        return NormalFrame.init()
                .bowl(new Score(8));
    }
}
