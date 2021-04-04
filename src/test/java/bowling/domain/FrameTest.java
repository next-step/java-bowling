package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    @DisplayName("턴 진행 확인")
    public void isEndFalse() throws Exception {
        //given
        Frame frame = Frame.of(5, NormalPins.init());

        //when

        //then
        assertThat(frame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("턴 종료 확인")
    public void isEndTrue() throws Exception {
        //given
        Frame frame = Frame.of(5, NormalPins.init()).next(3);

        //when

        //then
        assertThat(frame.isEnd()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"10,1:2", "1,9:2", "0,10:2"})
    @DisplayName("마지막 프레임은 스트라이크 or 스페어 때 최대 3번 투구")
    public void strikeOrSpareIs3Chances(int firstPin, String inputPins) throws Exception {

        //given
        Frame frame = Frame.of(firstPin, FinalPins.init());

        //when
        List<Integer> countOfDownPins = Arrays.stream(inputPins.split(":"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        countOfDownPins.forEach(pin -> frame.next(pin));

        List<Pin> pins = frame.pins().pins();

        //then
        assertThat(pins.size()).isEqualTo(3);
    }
}
