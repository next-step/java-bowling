package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.state.BeforeProgress;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {
    @DisplayName("Frames 를 생성한다.")
    @Test
    void Frames_생성() {
        List<Frame> frames = List.of(new NormalFrame(new BeforeProgress()));
        assertThat(new Frames(frames, 0)).isNotNull().isInstanceOf(Frames.class);
    }

    @DisplayName("Frames 생성 시 프레임 리스트가 null 일 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    void Frames_생성_예외_프레임리스트_null_인_경우(List<Frame> frames) {
        assertThatThrownBy(() -> new Frames(frames, 0)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Frames 생성 시 프레임 넘버가 0 ~ 9 를 벗어날 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 10, 11})
    void Frames_생성_예외_프레임넘버_null_인_경우(int currentFrameNumber) {
        List<Frame> normalFrames = List.of(new NormalFrame(new BeforeProgress()));
        assertThatThrownBy(() -> new Frames(normalFrames, currentFrameNumber)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("현재 프레임이 종료된 경우, 다음 프레임으로 넘어가기 위해 프레임 넘버를 1 증가시킨다.")
    @Test
    void nextFrame_다음프레임() {
        List<Frame> frames = new ArrayList<>();
        for (int frameNumber = 0; frameNumber < 9; frameNumber++) {
            frames.add(new NormalFrame(new Strike()));
        }
        Frames resultFrames = new Frames(frames, 0);
        resultFrames.nextFrame();
        assertThat(resultFrames.isCurrentFrameNumber(1)).isTrue();
    }

    @DisplayName("현재 프레임이 종료되었고, 현재 프레임이 마지막 프레임이면 프레임 넘버는 그대로 유지한다.")
    @Test
    void nextFrame_마지막프레임() {
        List<Frame> frames = new ArrayList<>();
        for (int frameNumber = 0; frameNumber < 9; frameNumber++) {
            frames.add(new NormalFrame(new Strike()));
        }
        frames.add(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Strike(), new Strike())), 3));

        Frames resultFrames = new Frames(frames, 9);
        resultFrames.nextFrame();
        assertThat(resultFrames.isCurrentFrameNumber(9)).isTrue();
    }

    @DisplayName("현재 프레임 넘버가 맞는지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "0, true",
            "1, false"
    })
    void isCurrentFrameNumber_확인(int currentFrameNumber, boolean trueOrFalse) {
        Frames frames = Frames.initialize();
        assertThat(frames.isCurrentFrameNumber(currentFrameNumber)).isEqualTo(trueOrFalse);
    }

    @DisplayName("Frames 의 마지막 프레임이 종료인 경우 true 를 반환한다.")
    @Test
    void isFinalFrameEnd_마지막_프레임_종료_true() {
        List<Frame> frames = new ArrayList<>();
        for (int frameNumber = 0; frameNumber < 9; frameNumber++) {
            frames.add(new NormalFrame(new Strike()));
        }
        frames.add(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Spare(new Pins(3)))), 3));
        assertThat(new Frames(frames, 9).isFinalFrameEnd()).isTrue();
    }

    @DisplayName("Frames 의 마지막 프레임이 종료되지 않은 경우 false 를 반환한다.")
    @Test
    void isFinalFrameEnd_마지막_프레임_종료_false() {
        List<Frame> frames = new ArrayList<>();
        for (int frameNumber = 0; frameNumber < 9; frameNumber++) {
            frames.add(new NormalFrame(new Strike()));
        }
        assertThat(new Frames(frames, 8).isFinalFrameEnd()).isFalse();
        frames.add(new FinalFrame(new LinkedList<>(List.of(new Strike())), 1));
        assertThat(new Frames(frames, 9).isFinalFrameEnd()).isFalse();
    }

    @DisplayName("모든 프레임의 점수를 누적한 리스트를 반환한다.")
    @Test
    void accumulateScores_누적점수_합() {
        List<Frame> frames = new ArrayList<>();
        for (int frameNumber = 0; frameNumber < 9; frameNumber++) {
            frames.add(new NormalFrame(new Strike()));
        }
        frames.add(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Strike(), new Strike())), 3));
        assertThat(new Frames(frames, 9).accumulateScores()).containsExactly(30, 60, 90, 120, 150, 180, 210, 240, 270, 300);
    }
}