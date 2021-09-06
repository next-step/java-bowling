package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @Test
    @DisplayName("객체 생성 - 스트라이크")
    void construct_strike() {
        //given
        Rollings rollings = Rollings.first(10);

        //when
        NormalFrame normalFrame = new NormalFrame(rollings);

        //then
        assertThat(normalFrame).isEqualTo(new NormalFrame(rollings));

    }

    @Test
    @DisplayName("객체 생성 - 스트라이크 아닌 경우")
    void construct_non_strike() {
        //given
        Rollings rollings = Rollings.first(5).second(5);

        //when
        NormalFrame normalFrame = new NormalFrame(rollings);

        //then
        assertThat(normalFrame).isEqualTo(new NormalFrame(rollings));

    }

    @Test
    @DisplayName("첫 프레임 번호")
    void frame_number() {
        //given
        Rollings rollings = Rollings.first(0).second(0);

        //when
        NormalFrame firstFrame = new NormalFrame(rollings);

        //then
        assertThat(firstFrame.number()).isEqualTo(1);

    }

    @Test
    @DisplayName("다음 프레임 번호")
    void next_frame_number() {
        //given
        Rollings rollings = Rollings.first(5).second(5);
        NormalFrame firstFrame = new NormalFrame(rollings);

        //when
        NormalFrame nextFrame = firstFrame.next(5);

        //then
        assertThat(nextFrame.number()).isEqualTo(2);

    }

    @Test
    @DisplayName("다음 프레임 생성")
    void next_frame() {
        //given
        int first = 10;
        Rollings rollings = Rollings.first(first);
        NormalFrame firstFrame = new NormalFrame(rollings);

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
        Rollings rollings = Rollings.first(first);
        NormalFrame firstFrame = new NormalFrame(rollings);

        //when

        //then
        assertThatThrownBy(() -> firstFrame.next(first)).isInstanceOf(CannotNextFrameException.class)
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
                Arguments.of(new NormalFrame(0).roll(10), true),//spare
                Arguments.of(new NormalFrame(1).roll(1), true),//miss
                Arguments.of(new NormalFrame(0).roll( 0), true),//gutter
                Arguments.of(new NormalFrame(0), false),
                Arguments.of(new NormalFrame(9), false)
        );
    }

}
