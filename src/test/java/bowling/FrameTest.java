package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    public void playGame(Frame frame, List<Integer> countOfDownPins) {
        countOfDownPins.forEach(downPin -> {
            if (!frame.isEnd()) {
                frame.next(downPin);
            }
        });
    }

    private List<Integer> stringToListInteger(String inputPins) {
        return Arrays.stream(inputPins.split(":"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

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
    @CsvSource(value = {"10,1:2,3", "1,9:2,3", "0,10:2,3"})
    @DisplayName("마지막 프레임은 스트라이크 or 스페어 때 한번 더 투구")
    public void strikeOrSpare3Chances(int firstPin, String inputPins, int count) throws Exception {
        //given
        Frame frame = Frame.of(firstPin, FinalPins.init());

        //when
        List<Integer> countOfDownPins = stringToListInteger(inputPins);
        playGame(frame, countOfDownPins);

        List<Pin> pins = frame.pins().pins();

        //then
        assertThat(pins.size()).isEqualTo(count);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1:2", "1,8:2", "0,9:2"})
    @DisplayName("마지막 프레임은 스트라이크 or 스페어 없을때 최대 2번 투구")
    public void notStrikeOrSpare2Chances(int firstPin, String inputPins) throws Exception {
        //given
        Frame frame = Frame.of(firstPin, FinalPins.init());

        //when
        List<Integer> countOfDownPins = stringToListInteger(inputPins);
        playGame(frame, countOfDownPins);

        List<Pin> pins = frame.pins().pins();

        //then
        assertThat(pins.size()).isEqualTo(2);
    }
}
