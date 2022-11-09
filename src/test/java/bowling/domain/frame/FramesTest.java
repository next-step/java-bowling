package bowling.domain.frame;

import bowling.domain.pin.FallenPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {
    @DisplayName("프레임들 초기화는 일반프레임으로 생성된다")
    @Test
    void init() {
        assertThat(Frames.init()).isEqualTo(new Frames(List.of(NormalFrame.init(1))));
    }

    @DisplayName("투구 후 프레임이 끝나지 않았으면 변경된 Frames 를 반환한다")
    @Test
    void bowl_returnThis() {
        Frames result = Frames.init()
                .bowl(FallenPin.of(8));

        assertThat(result).isEqualTo(new Frames(List.of(normalFrame().bowl(FallenPin.of(8)))));
    }

    @DisplayName("투구 후 프레임이 끝났다면 다음 Frames 를 반환한다")
    @Test
    void bowl_returnNext() {
        Frames result = Frames.init()
                .bowl(FallenPin.of(10));

        List<Frame> expectedFrames = new ArrayList<>();
        Frame normalFrame = normalFrame();
        Frame next = normalFrame.bowl(FallenPin.of(10));
        expectedFrames.add(normalFrame);
        expectedFrames.add(next);
        assertThat(result).isEqualTo(new Frames(expectedFrames));
    }

    @DisplayName("Frames 가 끝났는지 여부를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "11,false",
            "12,true"
    })
    void isOver(int tries, boolean expected) {
        Frames frames = frames(tries);
        assertThat(frames.isOver()).isEqualTo(expected);
    }

    @DisplayName("현재 프레임이 끝난 경우 참을 반환한다")
    @Test
    void isCurrentFrameFinished_true() {
        assertThat(frames(1).isCurrentFrameFinished()).isTrue();
    }

    @DisplayName("현재 프레임이 끝나지 않은 경우 거짓을 반환한다")
    @Test
    void isCurrentFrameFinished_false() {
        Frames frames = frames(1).bowl(FallenPin.of(9));

        assertThat(frames.isCurrentFrameFinished()).isFalse();
    }

    private Frame normalFrame() {
        return NormalFrame.init(1);
    }

    private Frames frames(int tries) {
        Frames result = Frames.init();
        for (int i = 0; i < tries; i++) {
            result = result.bowl(FallenPin.of(10));
        }
        return result;
    }
}
