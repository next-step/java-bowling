package bowling.domain;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.frame.Frames;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("모든 프레임에 같은 투구 후 마지막 결과 확인 테스트")
class RollingAndTerminateFrameResultTest {

    private Function<String, Integer> testPlayerFallenPinsService(final int fallenPins){
        return (s) -> fallenPins;
    }

    @DisplayName("모든 투구가 실패한 경우")
    @Test
    void allGutter() {
        Function<String, Integer> gutter = testPlayerFallenPinsService(0);
        List<String> ksgGutterBowlingGrade = Player.of("KSG")
                                                   .whileRollAndDisplay(Frames.MAX_FRAMES_SIZE, gutter)
                                                   .bowlingGrade();

        assertThat(ksgGutterBowlingGrade).last().isEqualTo("-");
    }

    @DisplayName("모두 미스 처리한 경우")
    @Test
    void allMiss() {
        Function<String, Integer> spare = testPlayerFallenPinsService(1);
        List<String> ksgSpareBowlingGrade = Player.of("KSG")
                                                  .whileRollAndDisplay(Frames.MAX_FRAMES_SIZE, spare)
                                                  .bowlingGrade();

        assertThat(ksgSpareBowlingGrade).last().isEqualTo("1|1");
    }

    @DisplayName("모두 스페이어로 처리한 경우")
    @Test
    void allSpare() {
        Function<String, Integer> spare = testPlayerFallenPinsService(5);
        List<String> ksgSpareBowlingGrade = Player.of("KSG")
                                                  .whileRollAndDisplay(Frames.MAX_FRAMES_SIZE, spare)
                                                  .bowlingGrade();

        assertThat(ksgSpareBowlingGrade).last().isEqualTo("5|/|5");
    }

    @DisplayName("모든 스트라이크로 처리한 경우")
    @Test
    void allStirke() {
        Function<String, Integer> stirke = testPlayerFallenPinsService(10);

        List<String> ksgStirkeBowlingGrade = Player.of("KSG")
                                                   .whileRollAndDisplay(Frames.MAX_FRAMES_SIZE, stirke)
                                                   .bowlingGrade();

        assertThat(ksgStirkeBowlingGrade).last().isEqualTo("X|X|X");
    }
}