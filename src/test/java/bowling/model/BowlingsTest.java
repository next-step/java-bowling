package bowling.model;

import bowling.model.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("볼링들")
class BowlingsTest {

    @Test
    @DisplayName("참가들로 생성")
    void instance() {
        assertThatNoException().isThrownBy(() -> Bowlings.fromNames(Collections.singletonList("asd")));
    }

    @Test
    @DisplayName("참가들은 필수")
    void instance_empty_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Bowlings.fromNames(Collections.emptyList()));
    }

    @ParameterizedTest(name = "[{index}] 0점짜리 {0}만큼 던지면 미종료 여부는 {1}")
    @DisplayName("2명의 볼링들 미종료 여부")
    @CsvSource({"39,true", "40,false"})
    void isNotFinished(int count, boolean expected) {
        //given
        Bowlings bowlings = Bowlings.fromNames(Arrays.asList("aaa", "bbb"));
        IntStream.range(0, count)
                .mapToObj(i -> Pins.ZERO)
                .forEach(bowlings::pitch);
        //when, then
        assertThat(bowlings.isNotFinished()).isEqualTo(expected);
    }

    @Test
    @DisplayName("프레임이 끝나면 번갈아가면서 공 추가")
    void pitch() {
        //given
        Bowlings bowlings = Bowlings.fromNames(Arrays.asList("aaa", "bbb"));
        IntStream.range(0, 3)
                .mapToObj(i -> Pins.MAX)
                .forEach(bowlings::pitch);
        //when, then
        assertThat(bowlings).isEqualTo(
                Bowlings.from(Arrays.asList(
                        Bowling.of(Participant.from("aaa"), Frames.init().bowling(Pins.MAX).bowling(Pins.MAX)),
                        Bowling.of(Participant.from("bbb"), Frames.init().bowling(Pins.MAX))
                )));
    }

    @ParameterizedTest(name = "[{index}] 10점짜리 {0}만큼 던지면 다음 순서는 {1}")
    @DisplayName("한 프레임이 끝나면 다음 참가자 순서")
    @CsvSource({"0,aaa", "1,bbb"})
    void nextParticipant(int count, String expected) {
        //given
        Bowlings bowlings = Bowlings.fromNames(Arrays.asList("aaa", "bbb"));
        IntStream.range(0, count)
                .mapToObj(i -> Pins.MAX)
                .forEach(bowlings::pitch);
        //when, then
        assertThat(bowlings.nextParticipant()).isEqualTo(Participant.from(expected));
    }
}
