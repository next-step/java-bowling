package bowling.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    @DisplayName("프레임을 만들면 첫번째 프레임이 생성되어있다.")
    @Test
    void createFrames() {
        Frames frames = new Frames();

        Frame firstFrame = frames.getCurrentFrame();

        assertThat(firstFrame.getFrameNumber().getNumber()).isEqualTo(1);
    }

    @DisplayName("다음 프레임을 생성해서 추가한다.")
    @Test
    void createNextFrame() {
        Frames frames = new Frames();

        frames.createNextFrame();
        Frame nextFrame = frames.getCurrentFrame();

        assertThat(nextFrame.getFrameNumber().getNumber()).isEqualTo(2);
    }

    @DisplayName("현재 진행 프레임에서 투구하고 남은핀을 반환한다.")
    @ParameterizedTest
    @CsvSource({"10, 0", "8, 2", "0, 10"})
    void bowlCurrentFrame(int pinCount, int leftPinCount) {
        Frames frames = new Frames();

        assertThat(frames.bowlCurrentFrame(pinCount)).isEqualTo(leftPinCount);
    }

    @DisplayName("현재 프레임에 투구 기회가 있는지 반환한다.")
    @Test
    void hasRemainChance() {
        Frames frames = new Frames();

        assertAll(
                () -> {
                    frames.bowlCurrentFrame(8);
                    assertThat(frames.hasRemainChance()).isTrue();
                },

                () -> {
                    frames.bowlCurrentFrame(1);
                    assertThat(frames.hasRemainChance()).isFalse();
                }
        );
    }

    @DisplayName("게임이 끝났는지 반환한다.")
    @Test
    void isEndAllFrames() {
        Frames frames = new Frames();

        for (int i = 0; i < 9; i++) {
            frames.createNextFrame();
        }

        assertAll(
                () -> {
                    frames.bowlCurrentFrame(8);
                    assertThat(frames.isEndAllFrames()).isFalse();
                },

                () -> {
                    frames.bowlCurrentFrame(1);
                    assertThat(frames.isEndAllFrames()).isTrue();
                }
        );
    }
}