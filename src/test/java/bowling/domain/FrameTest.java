package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FrameTest {

    @DisplayName("일반프레임 초기화는 볼링을 초기화하고 생성한다")
    @Test
    void init() {
        assertThat(Frame.init()).isEqualTo(new Frame(List.of(Bowling.init())));
    }

    @DisplayName("일반프레임 안의 투구들을 반환한다")
    @Test
    void bowlings() {
        List<Bowling> result = frame().getBowlings();

        List<Bowling> expected = List.of(bowling());
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("투구하고 그 결과를 반환한다")
    @Test
    void bowl() {
        Frame result = frame().bowl(FallenPins.of(2));

        Frame expected = new Frame(List.of(bowling().bowl(FallenPins.of(2))));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("투구가 끝나면 일반프레임이 끝난다")
    @Test
    void finished_whenBowlingFinished() {
        Frame frameWithTriesDoneBowling = frame().bowl(FallenPins.of(1));
        Frame frameWithStrikeBowling = Frame.init().bowl(FallenPins.of(10));

        assertAll(
                () -> assertThat(frameWithTriesDoneBowling.isFinished()).isTrue(),
                () -> assertThat(frameWithStrikeBowling.isFinished()).isTrue());
    }

    @DisplayName("투구가 끝나지 않으면 일반프레임이 끝나지 않는다.")
    @Test
    void notFinished_whenBowlingNotFinished() {
        assertThat(frame().isFinished()).isFalse();
    }

    private static Bowling bowling() {
        return Bowling.init()
                .bowl(FallenPins.of(8));
    }

    private Frame frame() {
        return Frame.init()
                .bowl(FallenPins.of(8));
    }
}
