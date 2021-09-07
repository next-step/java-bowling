package bowling.domain.frame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class FinalFrameTest {

    @ParameterizedTest(name = "모든 투구 완료 여부 {index} [{arguments}]")
    @MethodSource("all_rolled")
    @DisplayName("모든 투구 완료여부")
    void all_rolled(FinalFrame finalFrame, boolean expected) {
        //given

        //when
        boolean actual = finalFrame.isEnd();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);

    }

    private static Stream<Arguments> all_rolled() {
        return Stream.of(
                Arguments.of(new FinalFrame(10).roll(10).roll(10), true),
                Arguments.of(new FinalFrame(0).roll(10).roll(10), true),
                Arguments.of(new FinalFrame(1).roll(1), true),
                Arguments.of(new FinalFrame(0).roll(0), true),
                Arguments.of(new FinalFrame(0), false),
                Arguments.of(new FinalFrame(9), false)
        );
    }
}
