package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NormalFrameTest {
    public static Stream<Arguments> shotResultAndIsOverProvider() {
        ShotResults ten = new ShotResults();
        ten.add(ShotResult.TEN);

        ShotResults five = new ShotResults();
        five.add(ShotResult.FIVE);

        ShotResults fiveFive = new ShotResults();
        fiveFive.add(ShotResult.FIVE);
        fiveFive.add(ShotResult.FIVE);

        ShotResults zeroTen = new ShotResults();
        zeroTen.add(ShotResult.ZERO);
        zeroTen.add(ShotResult.TEN);

        return Stream.of(
            Arguments.of(
                ten, true
            ),
            Arguments.of(
                five, false
            ),
            Arguments.of(
                fiveFive, true
            ),
            Arguments.of(
                zeroTen, true
            )
        );
    }

    @MethodSource("shotResultAndIsOverProvider")
    @ParameterizedTest
    @DisplayName("프레임이 끝난는지 판단하는 테스트")
    public void checkOver(ShotResults shotResults, boolean isOver) {
        NormalFrame frame = new NormalFrame();

        for (ShotResult shotResult : shotResults) {
            frame.record(shotResult);
        }

        assertThat(frame.isOver()).isEqualTo(isOver);
    }
}