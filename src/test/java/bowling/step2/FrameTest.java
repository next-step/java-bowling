package bowling.step2;

import bowling.step2.domain.NormalFrame;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {

    @ParameterizedTest
    @CsvSource(value = {"10:10", "6:6"}, delimiter = ':')
    public void 투구_성공(int count, int expected) {
        //given
        NormalFrame normalFrame = NormalFrame.of(1);

        //when
        normalFrame.pitch(1, count);

        //then
        assertThat(normalFrame.total()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:10:10", "4:6:10", "1:1:2"}, delimiter = ':')
    public void 투구_성공2(int count1, int count2, int expected) {
        //given
        NormalFrame normalFrame = NormalFrame.of(1);

        //when
        normalFrame.pitch(1, count1);
        normalFrame.pitch(2, count2);

        //then
        assertThat(normalFrame.total()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:11", "11:6", "10:1"}, delimiter = ':')
    public void 투구_실패(int count1, int count2) {
        //given
        NormalFrame normalFrame = NormalFrame.of(1);

        //when, then
        assertThatThrownBy(() -> {
            normalFrame.pitch(1, count1);
            normalFrame.pitch(2, count2);
        }).isInstanceOf(RuntimeException.class);
    }
}
