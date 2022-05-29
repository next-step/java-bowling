package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.state.BeforeProgress;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {

    @DisplayName("Frames 를 생성한다.")
    @Test
    void Frames_생성() {
        List<Frame> frames = List.of(new NormalFrame(new BeforeProgress(), new FrameNumber(0)));
        assertThat(new Frames(frames)).isNotNull().isInstanceOf(Frames.class);
    }

    @DisplayName("Frames 생성 시 프레임 리스트가 null 일 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    void Frames_생성_예외(List<Frame> frames) {
        assertThatThrownBy(() -> new Frames(frames)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Frames 를 초기화 한다.")
    @Test
    void Frames_초기화() {
        List<Frame> frames = List.of(new NormalFrame(new BeforeProgress(), new FrameNumber(0)));
        assertThat(Frames.initialize()).isEqualTo(new Frames(frames));
    }

    @DisplayName("Frames 의 마지막 프레임이 종료인 경우 true 를 반환한다.")
    @Test
    void isFinalFrameEnd_마지막_프레임_종료_true() {
        List<Frame> frames = new ArrayList<>();
        for (int frameNumber = 0; frameNumber < 9; frameNumber++) {
            frames.add(new NormalFrame(new Strike(), new FrameNumber(frameNumber)));
        }
        frames.add(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Spare(new Pins(3)))), 3));
        assertThat(new Frames(frames).isFinalFrameEnd()).isTrue();
    }

    @DisplayName("Frames 의 마지막 프레임이 종료되지 않은 경우 false 를 반환한다.")
    @Test
    void isFinalFrameEnd_마지막_프레임_종료_false() {
        List<Frame> frames = new ArrayList<>();
        for (int frameNumber = 0; frameNumber < 9; frameNumber++) {
            frames.add(new NormalFrame(new Strike(), new FrameNumber(frameNumber)));
        }
        assertThat(new Frames(frames).isFinalFrameEnd()).isFalse();
        frames.add(new FinalFrame(new LinkedList<>(List.of(new Strike())), 1));
        assertThat(new Frames(frames).isFinalFrameEnd()).isFalse();
    }

    @DisplayName("Frames 의 현재 프레임이 진행 중이라면 다음 투구 진행 시 새로운 프레임이 추가되지 않는다.")
    @Test
    void bowl_추가_프레임_없음() {
        Frames frames = Frames.initialize();
        frames.bowl(new Pins(5));
        assertThat(frames.getCurrentFrameNumber()).isEqualTo(1);
    }

    @DisplayName("Frames 의 현재 프레임이 종료 됐다면 다음 투구 진행 시 새로운 프레임이 추가된다.")
    @Test
    void bowl_추가_프레임() {
        Frames frames = Frames.initialize();
        frames.bowl(new Pins(10));
        frames.bowl(new Pins(4));
        assertThat(frames.getCurrentFrameNumber()).isEqualTo(2);
    }
}