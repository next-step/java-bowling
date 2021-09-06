package bowling.domain.frame;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @Test
    @DisplayName("객체 생성 - 스트라이크")
    void construct_strike() {
        //given
        NormalFrame normalFrame = new NormalFrame(10);

        //when


        //then
        assertThat(normalFrame).isEqualTo(new NormalFrame(10));

    }

    @Test
    @DisplayName("객체 생성 - 스트라이크 아닌 경우")
    void construct_non_strike() {
        //given
        NormalFrame normalFrame = new NormalFrame(5, 5);

        //when


        //then
        assertThat(normalFrame).isEqualTo(new NormalFrame(5, 5));

    }

    @ParameterizedTest(name = "잘못된 투구 {index} [{arguments}]")
    @CsvSource(value = {
            "10,1",//초구 스트라이크 이후 투구
            "1,10",//2구 쓰러트린 핀 수 초과
    })
    @DisplayName("객체 생성 실패")
    void construct_exception(int first, int second) {
        //given

        //when
        ThrowableAssert.ThrowingCallable actual = () -> new NormalFrame(first, second);


        //then
        assertThatThrownBy(actual).isInstanceOf(RollingsException.class);

    }

    @ParameterizedTest(name = "유효범위 벗어난 투구 점수 {index} [{arguments}]")
    @CsvSource(value = {
            "11,1",//초구 쓰러트린 핀 수 초과
            "-1,0"//쓰러트린 핀 수 마이너스
    })
    @DisplayName("객체 생성 실패")
    void wrong_rolling(int first, int second) {
        //given

        //when
        ThrowableAssert.ThrowingCallable actual = () -> new NormalFrame(first, second);


        //then
        assertThatThrownBy(actual).isInstanceOf(RollingException.class);

    }

    @Test
    @DisplayName("첫 프레임 번호")
    void frame_number() {
        //given
        int first = 0;
        int second = 0;

        //when
        NormalFrame firstFrame = new NormalFrame(first, second);

        //then
        assertThat(firstFrame.number()).isEqualTo(1);

    }

    @Test
    @DisplayName("다음 프레임 번호")
    void next_frame_number() {
        //given
        int first = 5;
        int second = 5;
        NormalFrame firstFrame = new NormalFrame(first, second);

        //when
        NormalFrame nextFrame = firstFrame.next(first);

        //then
        assertThat(nextFrame.number()).isEqualTo(2);

    }

    @Test
    @DisplayName("다음 프레임 생성")
    void next_frame() {
        //given
        int first = 10;
        NormalFrame firstFrame = new NormalFrame(first);

        //when
        NormalFrame nextFrame = firstFrame.next(first);

        //then
        assertThat(nextFrame.number()).isEqualTo(2);

    }

    @Test
    @DisplayName("다음 프레임 생성 불가")
    void next_frame_exception() {
        //given
        int first = 5;
        NormalFrame firstFrame = new NormalFrame(first);

        //when

        //then
        assertThatThrownBy(() -> firstFrame.next(5)).isInstanceOf(CannotNextFrameException.class)
                .hasMessage("모든 투구를 완료하지 않아 다음 프레임으로 진행하지 못합니다.");

    }

    @ParameterizedTest(name = "모든 투구 완료 여부 {index} [{arguments}]")
    @MethodSource("all_rolled")
    @DisplayName("모든 투구 완료여부")
    void all_rolled(NormalFrame normalFrame, boolean expected) {
        //given

        //when
        boolean actual = normalFrame.allRolled();

        //then
        assertThat(actual).isEqualTo(expected);

    }

    private static Stream<Arguments> all_rolled() {
        return Stream.of(
                Arguments.of(new NormalFrame(10), true),//strike
                Arguments.of(new NormalFrame(0, 10), true),//spare
                Arguments.of(new NormalFrame(1, 1), true),//miss
                Arguments.of(new NormalFrame(0, 0), true),//gutter
                Arguments.of(new NormalFrame(0), false),
                Arguments.of(new NormalFrame(9), false)
        );
    }

}
