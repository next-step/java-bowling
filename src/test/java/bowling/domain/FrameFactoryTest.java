package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameFactoryTest {
    public static Stream<Arguments> provideIndexAndClass() {
        return Stream.of(
                Arguments.of(8, NormalFrame.class),
                Arguments.of(9, BonusFrame.class)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIndexAndClass")
    void 프레임_완료_후_보너스_프레임_추가(int index, Class classType) {
        assertThat(FrameFactory.create(index)).isInstanceOf(classType);
    }
}
