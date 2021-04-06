package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("점수")
public class ScoreTest {

    public Frame createFrame(int countOfPins, Pins pins) {
        return Frame.of(countOfPins, pins);
    }

    private List<Integer> stringToIntegers(String pins) {
        return Arrays.stream(pins.split(":"))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
    }

    @Test
    @DisplayName("스트라이크 점수")
    public void strikeScore() throws Exception {
        //given
        Frame frame = createFrame(10, NormalPins.init());
        FrameScore score = FrameScore.of(frame);
        //when

        //then
        assertThat(score.score()).isEqualTo(10);
    }

    @Test
    @DisplayName("스페어 점수")
    public void spareScore() throws Exception {
        //given
        Frame frame = createFrame(1, NormalPins.init());
        frame.next(9);
        FrameScore score = FrameScore.of(frame);
        //when

        //then
        assertThat(score.score()).isEqualTo(10);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0,0", "1:2,3", "3:4,7", "5:4,9"})
    @DisplayName("기타 점수")
    public void etcScore(String pins, int result) throws Exception {
        //given
        List<Integer> pin = stringToIntegers(pins);
        Frame frame = createFrame(pin.get(0), NormalPins.init());
        frame.next(pin.get(1));

        //when
        FrameScore score = FrameScore.of(frame);

        //then
        assertThat(score.score()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:1:1,12", "10:1:2,13", "10:3:4,17", "10:5:0,15"})
    @DisplayName("스트라이크는 보너스 점수 2번 추가")
    public void strikeIsBonusChance(String pins, int result) throws Exception {
        //given
        List<Integer> pin = stringToIntegers(pins);
        Frame frame = createFrame(pin.get(0), NormalPins.init());

        //when
        FrameScore score = FrameScore.of(frame)
                        .add(pin.get(1))
                        .add(pin.get(2));

        //then
        assertThat(score.score()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"9:1:1:1,11", "9:1:5:3,15", "7:3:4:3,14", "9:1:0:0,10"})
    @DisplayName("스페어는 보너스 점수 1번 추가")
    public void spareIsBonusChance(String pins, int result) throws Exception {
        //given
        List<Integer> pin = stringToIntegers(pins);
        Frame frame = createFrame(pin.get(0), NormalPins.init()).next(pin.get(1));

        //when
        FrameScore score = FrameScore.of(frame)
                .add(pin.get(2))
                .add(pin.get(3));

        //then
        assertThat(score.score()).isEqualTo(result);
    }

    @Test
    @DisplayName("프레임 끝나기전 생성 시 에러 발생")
    public void exception() throws Exception {
        //given
        Frame frame = createFrame(5, NormalPins.init());

        //when
        assertThatThrownBy(() -> FrameScore.of(frame))
                       .isInstanceOf(IllegalArgumentException.class);

        //then

    }
}
