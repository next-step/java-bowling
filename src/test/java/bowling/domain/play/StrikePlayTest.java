package bowling.domain.play;

import bowling.domain.Roll;
import bowling.domain.RollSubject;
import bowling.dto.RollDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class StrikePlayStateTest {
    private final PlayState state = StrikePlayState.getInstance();
    private RollSubject subject;
    private PlayContext context;

    @Test
    @DisplayName("첫번째가 strike 아니면, NormalPlayState 로 변화한다.")
    void playFirst() {
        subject = new RollSubject(() -> Roll.of(5));
        context = new PlayContext(subject);
        state.playFirst(context);
        context.play(5);
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(5, 5, 5));
    }

    @Test
    @DisplayName("playSecond 를 해도 변화가 없다.")
    void playSecond() {
        subject = new RollSubject(() -> Roll.of(1));
        context = new PlayContext(subject);
        for (int i = 0; i < 100; i++) {
            state.playSecond(context, 5);
        }
        assertThat(toRolls(context))
                .isEqualTo(emptyList());
    }

    @Test
    @DisplayName("playBonus 를 해도 변화가 없다.")
    void playBonus() {
        subject = new RollSubject(() -> Roll.of(1));
        context = new PlayContext(subject);
        for (int i = 0; i < 100; i++) {
            state.playBonus(context);
        }
        assertThat(toRolls(context))
                .isEqualTo(emptyList());
    }

    private List<Integer> toRolls(PlayContext context) {
        return context.exportRollsDto()
                .getRolls()
                .stream()
                .map(RollDto::getCountOfPins)
                .collect(toList());
    }
}
