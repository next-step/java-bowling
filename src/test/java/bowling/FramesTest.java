package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("여러 프레임")
public class FramesTest {

    public static final Player P1 = new Player("TDD");


    public void playGame(Frames frames, List<Integer> countOfDownPins) {
        countOfDownPins.forEach(downPin -> {
            if (frames.isPlay()) {
                frames.play(downPin);
            }
        });
    }

    @Test
    @DisplayName("frames 생성 확인")
    public void frames() throws Exception {
        //given
        Frames frames = Frames.of(P1);
        //when

        //then
        assertThat(frames).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"10,1", "2:8:3,2", "2:8:10:10,3", "10:10:10:10:10,5", "10:10:10:10:10:10:10:10:10:10,10", "10:10:10:10:10:10:10:10:10:10:10:10:10,10"})
    @DisplayName("게임은 최대 10턴까지 진행")
    public void play(String pins, int turn) throws Exception {
        //given
        Frames frames = Frames.of(P1);

        //when
        List<Integer> countOfDownPins = Arrays.stream(pins.split(":"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        playGame(frames, countOfDownPins);

        int size = frames.frames().size();

        //then
        assertThat(size).isEqualTo(turn);
    }


}
