package bowling.domain;

import bowling.domain.State.StateType;
import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.PinCount;
import bowling.dto.NormalFrameResult;
import bowling.dto.PinCountsResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class NormalFrameTest {


    @ParameterizedTest
    @CsvSource(value = {"1,3,4", "10,8","8,8","5,6","7,10"}, delimiter = ':')
    @DisplayName("유효하지 투구값으로 생성")
    void create_from_invalid_pin_count_list_throw_exception(String pinCountsInString) {
        String nameSeparator = ",";
        String[] pinCountsInArray = pinCountsInString.split(nameSeparator);
        List<PinCount> list = Arrays.stream(pinCountsInArray)
                .map(pinCountInString -> new PinCount(Integer.parseInt(pinCountInString)))
                .collect(Collectors.toList());
        assertThatIllegalArgumentException().isThrownBy(() ->
                NormalFrame.of(new FrameNumber(10),list,null)
        );
    }

    @Test
    void first() {
        NormalFrame first = NormalFrame.first();
        NormalFrameResult result = first.result();

        assertThat(result.frameNumber()).isEqualTo(1);
    }

    @Test
    void next() {
        NormalFrame nextThree = NormalFrame.first().next().next();
        NormalFrameResult result = nextThree.result();

        assertThat(result.frameNumber()).isEqualTo(3);
    }

    @Test
    void add_pin_count() {
        int pinCount = 6;
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.addPinCount(pinCount);

        PinCountsResult result = normalFrame.result().pinCountsResult();
        assertThat(result.stateType()).isEqualTo(StateType.NONE);
        assertThat(result.pinCounts()).containsExactly(pinCount);
    }

    @Test
    void add_pin_counts_when_done_throw_exception() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.addPinCount(4);
        normalFrame.addPinCount(5);

        assertThat(normalFrame.isDone()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                normalFrame.addPinCount(5));
    }

    @Test
    void result_when_strike() {
        NormalFrame normalFrame = NormalFrame.first().next();
        int strikePinCounts = 10;
        normalFrame.addPinCount(strikePinCounts);

        PinCountsResult result = normalFrame.result().pinCountsResult();
        assertThat(normalFrame.isDone()).isTrue();
        assertThat(result.stateType()).isEqualTo(StateType.STRIKE);
        assertThat(result.pinCounts()).containsExactly(strikePinCounts);
    }


    @Test
    void result_when_spare() {
        NormalFrame normalFrame = NormalFrame.first().next();
        int firstPinCount = 2;
        int secondPinCount = 8;
        normalFrame.addPinCount(firstPinCount);
        normalFrame.addPinCount(secondPinCount);

        PinCountsResult result = normalFrame.result().pinCountsResult();
        assertThat(normalFrame.isDone()).isTrue();
        assertThat(result.stateType()).isEqualTo(StateType.SPARE);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(firstPinCount,secondPinCount);
    }

    @Test
    void result_when_miss() {
        NormalFrame normalFrame = NormalFrame.first();
        int firstPinCount = 2;
        int secondPinCount = 6;
        normalFrame.addPinCount(firstPinCount);
        normalFrame.addPinCount(secondPinCount);

        PinCountsResult result = normalFrame.result().pinCountsResult();
        assertThat(normalFrame.isDone()).isTrue();
        assertThat(result.stateType()).isEqualTo(StateType.MISS);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(firstPinCount,secondPinCount);
    }

    @Test
    void result_when_none() {
        NormalFrame normalFrame = NormalFrame.first();
        int firstPinCount = 2;
        normalFrame.addPinCount(firstPinCount);

        PinCountsResult result = normalFrame.result().pinCountsResult();
        assertThat(normalFrame.isDone()).isFalse();
        assertThat(result.stateType()).isEqualTo(StateType.NONE);
        assertThat(result.pinCounts()).containsExactly(firstPinCount);
    }


}
