package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    @DisplayName("프레임, 턴 진행 확인")
    public void first() throws Exception {
        //given
        Frame frame = Frame.first(5, NormalPins.init());

        //when

        //then
        assertThat(frame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("프레임, 턴 종료 확인")
    public void second() throws Exception {
        //given
        Frame frame = Frame.first(5, NormalPins.init()).next(3);

        //when

        //then
        assertThat(frame.isEnd()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,10})
    @DisplayName("첫구 넘어진 핀 확인")
    public void firstDownPin(int first) throws Exception {
        //given
        Frame frame = Frame.first(first, NormalPins.init());
        //when

        //then
//        assertThat(frame.pin().firstBall()).isEqualTo(first);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,10})
    @DisplayName("2 넘어진 핀 확인")
    public void secondDownPin(int second) throws Exception {
        //given
        Frame frame = Frame.first(0, NormalPins.init()).next(second);

        //when

        //then
//        assertThat(frame.pin().secondBall()).isEqualTo(second);
    }
}
