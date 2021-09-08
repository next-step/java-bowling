package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FrameResultTest {
    public static Stream<Arguments> shotResultAndFrameResultProvider() {
        ShotResults ten = new ShotResults();
        ten.add(ShotResult.TEN);

        ShotResults zeroTen = new ShotResults();
        zeroTen.add(ShotResult.ZERO);
        zeroTen.add(ShotResult.TEN);

        ShotResults fourSix = new ShotResults();
        fourSix.add(ShotResult.FOUR);
        fourSix.add(ShotResult.SIX);

        ShotResults fourThree = new ShotResults();
        fourThree.add(ShotResult.FOUR);
        fourThree.add(ShotResult.THREE);

        return Stream.of(
            Arguments.of(
                ten, FrameResult.STRIKE
            ),
            Arguments.of(
                zeroTen, FrameResult.SPARE
            ),
            Arguments.of(
                fourSix, FrameResult.SPARE
            ),
            Arguments.of(
                fourThree, FrameResult.MISS
            )
        );
    }

    @MethodSource("shotResultAndFrameResultProvider")
    @ParameterizedTest
    @DisplayName("프레임 결과 확인 테스트")
    public void getFrameResult(ShotResults shotResults, FrameResult frameResult) {
        assertThat(FrameResult.fromShotResults(shotResults)).isEqualTo(frameResult);
    }
}