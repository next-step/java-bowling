package bowling;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.NormalFrame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    @Test
    void 첫번째_프레임_생성() {
        assertThat(NormalFrame.ofFirst()).isInstanceOf(NormalFrame.class);
    }

    @Test
    void 다음_프레임_생성() {
        assertThat(NormalFrame.ofFirst().addNextFrame()).isInstanceOf(NormalFrame.class);
    }

    @Test
    void 마지막_프레임_생성() {
        assertThat(NormalFrame.ofFinal()).isInstanceOf(FinalFrame.class);
    }

    @Test
    void 프레임8에서_일반_프레임_생성() {
        assertThat(NormalFrame.of(8).addNextFrame()).isInstanceOf(NormalFrame.class);
    }

    @Test
    void 프레임9에서_마지막_프레임_생성() {
        assertThat(NormalFrame.of(9).addNextFrame()).isInstanceOf(FinalFrame.class);
    }

    @Test
    void 일반_프레임_종료_확인_스트라이크() {
        Frame frame = NormalFrame.ofFirst();
        frame.bowl(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @ParameterizedTest
    @MethodSource(value = "provideKnockOutCounts")
    void 일반_프레임_종료_확인_스페어와_두번투구(int first, int second) {
        Frame frame = NormalFrame.ofFirst();
        frame.bowl(first);
        frame.bowl(second);
        assertThat(frame.isEnd()).isTrue();
    }

    private static Stream<Arguments> provideKnockOutCounts() {
        return Stream.of(
                Arguments.of(5, 5),
                Arguments.of(5, 4)
        );
    }

}
