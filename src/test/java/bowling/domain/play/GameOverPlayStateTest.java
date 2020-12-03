package bowling.domain.play;

import bowling.domain.Roll;
import bowling.domain.RollSubject;
import bowling.dto.RollDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class GameOverPlayStateTest {
    private final PlayState state = GameOverPlayState.getInstance();
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
            state.playFirst(context, i);
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
    @DisplayName("playBonus 를 해도 변화가 없다.")
    void playBonus() {
        for (int i = 0; i < 100; i++) {
            state.playBonus(context, i);
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
