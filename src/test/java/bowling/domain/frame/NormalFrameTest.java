package bowling.domain.frame;

import bowling.domain.rolling.NormalRollings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class NormalFrameTest {

    @Test
    @DisplayName("객체 생성 - 스트라이크")
    void construct_strike() {
        //given
        NormalRollings normalRollings = NormalRollings.first(10);

        //when
        NormalFrame normalFrame = new NormalFrame(normalRollings);

        //then
        assertThat(normalFrame).isEqualTo(new NormalFrame(normalRollings));

    }

    @Test
    @DisplayName("객체 생성 - 스트라이크 아닌 경우")
    void construct_non_strike() {
        //given
        NormalRollings normalRollings = NormalRollings.first(5).second(5);

        //when
        NormalFrame normalFrame = new NormalFrame(normalRollings);

        //then
        assertThat(normalFrame).isEqualTo(new NormalFrame(normalRollings));

    }

    @Test
    @DisplayName("첫 프레임 번호")
    void frame_number() {
        //given
        NormalRollings normalRollings = NormalRollings.first(0).second(0);

        //when
        NormalFrame firstFrame = new NormalFrame(normalRollings);

        //then
        assertThat(firstFrame.number()).isEqualTo(1);

    }

    @Test
    @DisplayName("다음 프레임 번호")
    void next_frame_number() {
        //given
        NormalRollings normalRollings = NormalRollings.first(5).second(5);
        NormalFrame firstFrame = new NormalFrame(normalRollings);

        //when
        Frame nextFrame = firstFrame.next();

        //then
        assertThat(nextFrame.number()).isEqualTo(2);

    }

    @Test
    @DisplayName("다음 프레임 생성")
    void next_frame() {
        //given
        int first = 10;
        NormalRollings normalRollings = NormalRollings.first(first);
        NormalFrame firstFrame = new NormalFrame(normalRollings);

        //when
        Frame nextFrame = firstFrame.next();

        //then
        assertThat(nextFrame.number()).isEqualTo(2);

    }

    @Test
    @DisplayName("다음 프레임 생성 불가")
    void next_frame_exception() {
        //given
        int first = 5;
        NormalRollings normalRollings = NormalRollings.first(first);
        NormalFrame firstFrame = new NormalFrame(normalRollings);

        //when

        //then
        assertThatThrownBy(firstFrame::next).isInstanceOf(CannotNextFrameException.class)
                .hasMessage("모든 투구를 완료하지 않아 다음 프레임으로 진행하지 못합니다.");

    }

    @ParameterizedTest(name = "모든 투구 완료 여부 {index} [{arguments}]")
    @MethodSource("all_rolled")
    @DisplayName("모든 투구 완료여부")
    void all_rolled(NormalFrame normalFrame, boolean expected) {
        //given

        //when
        boolean actual = normalFrame.isEnd();

        //then
        assertThat(actual).isEqualTo(expected);

    }

    private static Stream<Arguments> all_rolled() {
        return Stream.of(
                Arguments.of(new NormalFrame(10), true),//strike
                Arguments.of(new NormalFrame(0).roll(10), true),//spare
                Arguments.of(new NormalFrame(1).roll(1), true),//miss
                Arguments.of(new NormalFrame(0).roll( 0), true),//gutter
                Arguments.of(new NormalFrame(0), false),
                Arguments.of(new NormalFrame(9), false)
        );
    }

    @Test
    @DisplayName("9번 프레임 이후 10번 프레임")
    void final_frame() {
        //given
        int numberOfFrame = 9;
        int strike = 10;
        NormalFrame nineFrame = new NormalFrame(NormalRollings.first(strike), numberOfFrame);

        //when
        Frame next = nineFrame.next();

        //then
        assertThat(next).isInstanceOf(FinalFrame.class);

    }
}
