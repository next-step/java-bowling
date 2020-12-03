package bowling.domain.play;

import bowling.domain.Roll;
import bowling.domain.RollSubject;
import bowling.dto.RollDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class LastStrikePlayStateTest {
    private final PlayState state = LastStrikePlayState.getInstance();
    private RollSubject subject;
    private PlayContext context;

    @BeforeEach
    void setUp() {
        subject = new RollSubject(() -> Roll.of(10));
        context = new PlayContext(subject);
    }

    @Test
    @DisplayName("playFirst 를 해도 변화가 없다.")
    void playFirst() {
        for (int i = 0; i < 100; i++) {
            state.playFirst(context);
        }
        assertThat(toRolls(context))
                .isEqualTo(emptyList());
    }

    @Test
    @DisplayName("playSecond 를 해도 변화가 없다.")
    void playSecond() {
        for (int i = 0; i < 100; i++) {
            state.playSecond(context, i);
        }
        assertThat(toRolls(context))
                .isEqualTo(emptyList());
    }

    @Test
    @DisplayName("마지막 프레임에서 playBonus 를 하면 GameOverPlayState 로 변화한다.")
    void playBonus() {
        state.playBonus(context);
        state.playBonus(context);
        for (int i = 0; i < 100; i++) {
            context.play(i);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(10, 10, 10, 10));
    }

    private List<Integer> toRolls(PlayContext context) {
        return context.exportRollsDto()
                .getRolls()
                .stream()
                .map(RollDto::getCountOfPins)
                .collect(toList());
    }
}
