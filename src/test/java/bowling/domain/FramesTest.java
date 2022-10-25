package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("Frames 초기화는 일반프레임을 초기화해서 생성한다")
    @Test
    void init() {
        assertThat(Frames.init()).isEqualTo(new Frames(List.of(frame())));
    }

    @DisplayName("투구한 결과가 반환된다")
    @Test
    void bowl() {
        Frames result = Frames.init()
                .bowl(new Score(10));

        assertThat(result).isEqualTo(new Frames(List.of(frame().bowl(new Score(10)))));
    }

    @DisplayName("다음 Frames 를 반환한다")
    @Test
    void next() {
        Frames result = Frames.init()
                .bowl(new Score(10))
                .next();

        Frames expected = new Frames(List.of(
                frame().bowl(new Score(10)),
                frame()));
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("9번째 다음은 최종프레임을 넣어 Frames 를 반환한다")
    @Test
    void next_when9Frames() {
        Frames result = frames(9).next();

        List<Frame> frameList = frameList(9);
        frameList.add(finalFrame());
        Frames expected = new Frames(frameList);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("Frames 가 끝났는지 여부를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "11,false",
            "12,true"
    })
    void isOver(int frameCount, boolean expected) {
        assertThat(frames(frameCount).isOver()).isEqualTo(expected);
    }

    @DisplayName("마지막 프레임넘버를 반환한다")
    @Test
    void lastFrameNumber() {
        assertThat(frames(5).lastFrameNumber()).isEqualTo(6);
    }

    private Frame frame() {
        return Frame.init();
    }

    private Frame finalFrame() {
        return FinalFrame.init();
    }

    private Frames frames(int frameCount) {
        Frames result = Frames.init();
        for (int i = 0; i < frameCount; i++) {
            result = result.bowl(new Score(10));
            result = result.next();
        }
        return result;
    }

    private List<Frame> frameList(int frameCount) {
        List<Frame> result = new ArrayList<>();
        for (int i = 0; i < frameCount; i++) {
            result.add(frame().bowl(new Score(10)));
        }
        return result;
    }
}
