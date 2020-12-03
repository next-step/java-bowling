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

class NormalPlayStateTest {
    private final PlayState state = NormalPlayState.getInstance();
    private RollSubject subject;
    private PlayContext context;

    @Test
    @DisplayName("첫번째가 strike 이면, StrikePlayState 로 변화한다.")
    void playFirst() {
        subject = new RollSubject(() -> Roll.of(10));
        context = new PlayContext(subject);
        state.playFirst(context);
        context.play(5);
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(10, 10));
    }

    @Test
    @DisplayName("두번째에서 frameNo 가 10이면, GameOverState 로 변화한다.")
    void playSecond_GameOver() {
        subject = new RollSubject(() -> Roll.of(1));
        context = new PlayContext(subject);
        state.playSecond(context, 5);
        state.playSecond(context, 10);
        for (int i = 0; i < 100; i++) {
            context.play(i);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(1, 1));
    }

    @Test
    @DisplayName("마지막 프레임이고 Spare 이면, LastSparePlayState 로 변화한다.")
    void playSecond_LastSpare() {
        subject = new RollSubject(() -> Roll.of(5));
        context = new PlayContext(subject);
        context.play(9);
        state.playSecond(context, 10);
        state.playSecond(context, 10);
        context.play(10);
        for (int i = 0; i < 100; i++) {
            context.play(i);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(5, 5, 5, 5, 5));
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
