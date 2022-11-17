package bowling.step4.domain.frame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class FramesTest {

    @Test
    void 열개의_프레임_생성() {
        Frames frame = new Frames();
        assertSoftly(softly -> {
            assertThat(frame.frameMap().size()).isEqualTo(10);
            assertThat(frame.frameMap().get(9)).isInstanceOf(NormalFrame.class);
            assertThat(frame.frameMap().get(10)).isInstanceOf(FinalFrame.class);
        });
    }

    @ParameterizedTest
    @CsvSource(value = {"10,true", "8,false"})
    void 노멀프레임_프레임_종료_테스트(int count, Boolean expected) {
        Frames frames = new Frames();
        frames.bowl(2, count);
        assertThat(frames.isEndedFrame(2)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"10,false", "8,false"})
    void 파이널판_프레임_종료_테스트(int count, Boolean expected) {
        Frames frames = new Frames();
        frames.bowl(10, count);
        assertThat(frames.isEndedFrame(10)).isEqualTo(expected);
    }
}
