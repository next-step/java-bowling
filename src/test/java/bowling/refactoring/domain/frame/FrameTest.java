package bowling.refactoring.domain.frame;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static org.assertj.core.api.Assertions.*;

class FrameTest {

    @ParameterizedTest
    @CsvSource(value = {"10,true", "8,false"})
    void 노멀프레임_프레임_종료_테스트(int count, Boolean expected) {
        Frame frame = new NormalFrame();
        frame.bowl(count);
        assertThat(frame.isEnd()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"10,false", "8,false"})
    void 파이널판_프레임_종료_테스트(int count, Boolean expected) {
        Frame frame = new FinalFrame();
        frame.bowl(count);
        assertThat(frame.isEnd()).isEqualTo(expected);
    }
}
