package bowling.domain.frame;

import bowling.bowlingexception.InvalidProgressException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameProgressTest {

    @Test
    @DisplayName("프레임에서 첫 피치를 진행하지 않았을 때의 조건")
    void testNotYetStarted() {
        List<DownedPin> empty = new ArrayList<>();

        assertThat(NormalFrameProgress.getProgress(empty))
                .isEqualTo(NormalFrameProgress.ON_FIRST_PITCH);
    }

    @Test
    @DisplayName("1회 투구를 진행 했을 때")
    void testStart() {
        List<DownedPin> one = new ArrayList<>();
        one.add(DownedPin.fromNumber(4));

        assertThat(NormalFrameProgress.getProgress(one))
                .isEqualTo(NormalFrameProgress.ON_SECOND_PITCH);
    }

    @Test
    @DisplayName("최대 2회의 투구 기회를 소진하였을 때")
    void testEnd() {
        List<DownedPin> two = new ArrayList<>();
        DownedPin first = DownedPin.fromNumber(4);
        DownedPin second = first.fromSubordinateTry(first);

        two.add(first);
        two.add(second);

        assertThat(NormalFrameProgress.getProgress(two))
                .isEqualTo(NormalFrameProgress.END);
    }

    @Test
    @DisplayName("허용되지 않는 투구기회")
    void notAllowedPitch() {
        List<DownedPin> downedPins = new ArrayList<>();
        DownedPin first = DownedPin.fromNumber(4);
        DownedPin second = first.fromSubordinateTry(DownedPin.fromNumber(2));
        DownedPin third = second.fromSubordinateTry(DownedPin.fromNumber(1));

        downedPins.add(first);
        downedPins.add(second);

        downedPins.add(third);

        assertThatThrownBy(() -> NormalFrameProgress.getProgress(downedPins))
                .isInstanceOf(InvalidProgressException.class);
    }
}
