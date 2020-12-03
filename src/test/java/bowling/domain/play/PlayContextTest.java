package bowling.domain.play;

import bowling.domain.Roll;
import bowling.domain.RollSubject;
import bowling.dto.RollDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class PlayContextTest {
    @ParameterizedTest
    @DisplayName("콜백 함수의 register 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void register(int executeTimes) {
        List<Integer> list = new LinkedList<>();
        RollSubject subject = new RollSubject(() -> Roll.of(1));
        PlayContext context = new PlayContext(subject);
        context.register(() -> list.add(0));
        for (int frameNo = 1; frameNo <= executeTimes; frameNo++) {
            context.execute();
        }
        assertThat(list.size())
                .isEqualTo(executeTimes);
    }

    @Test
    @DisplayName("한번 STRIKE 인 경우 테스트")
    void strike_1() {
        int playTimes = 1;
        RollSubject subject = new RollSubject(() -> Roll.of(10));
        PlayContext context = new PlayContext(subject);
        for (int frameNo = 1; frameNo <= playTimes; frameNo++) {
            context.play(frameNo);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(10));
    }

    @Test
    @DisplayName("두번 STRIKE 인 경우 테스트")
    void strike_2() {
        int playTimes = 2;
        RollSubject subject = new RollSubject(() -> Roll.of(10));
        PlayContext context = new PlayContext(subject);
        for (int frameNo = 1; frameNo <= playTimes; frameNo++) {
            context.play(frameNo);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(10, 10));
    }

    @Test
    @DisplayName("10번 STRIKE 인 경우 테스트")
    void strike_10() {
        int playTimes = 10;
        RollSubject subject = new RollSubject(() -> Roll.of(10));
        PlayContext context = new PlayContext(subject);
        for (int frameNo = 1; frameNo <= playTimes; frameNo++) {
            context.play(frameNo);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
    }

    @Test
    @DisplayName("100번 STRIKE 인 경우 테스트")
    void strike_100() {
        int playTimes = 100;
        RollSubject subject = new RollSubject(() -> Roll.of(10));
        PlayContext context = new PlayContext(subject);
        for (int frameNo = 1; frameNo <= playTimes; frameNo++) {
            context.play(frameNo);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10));
    }

    @Test
    @DisplayName("한번 SPARE 인 경우 테스트")
    void spare_1() {
        int playTimes = 1;
        RollSubject subject = new RollSubject(() -> Roll.of(5));
        PlayContext context = new PlayContext(subject);
        for (int frameNo = 1; frameNo <= playTimes; frameNo++) {
            context.play(frameNo);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(5, 5));
    }

    @Test
    @DisplayName("두번 SPARE 인 경우 테스트")
    void spare_2() {
        int playTimes = 2;
        RollSubject subject = new RollSubject(() -> Roll.of(5));
        PlayContext context = new PlayContext(subject);
        for (int frameNo = 1; frameNo <= playTimes; frameNo++) {
            context.play(frameNo);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(5, 5, 5, 5));
    }

    @Test
    @DisplayName("10번 SPARE 인 경우 테스트")
    void spare_10() {
        int playTimes = 10;
        RollSubject subject = new RollSubject(() -> Roll.of(5));
        PlayContext context = new PlayContext(subject);
        for (int frameNo = 1; frameNo <= playTimes; frameNo++) {
            context.play(frameNo);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
    }

    @Test
    @DisplayName("100번 SPARE 인 경우 테스트")
    void spare_100() {
        int playTimes = 100;
        RollSubject subject = new RollSubject(() -> Roll.of(5));
        PlayContext context = new PlayContext(subject);
        for (int frameNo = 1; frameNo <= playTimes; frameNo++) {
            context.play(frameNo);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5));
    }

    @Test
    @DisplayName("한번 MISS 인 경우 테스트")
    void miss_1() {
        int playTimes = 1;
        RollSubject subject = new RollSubject(() -> Roll.of(1));
        PlayContext context = new PlayContext(subject);
        for (int frameNo = 1; frameNo <= playTimes; frameNo++) {
            context.play(frameNo);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(1, 1));
    }

    @Test
    @DisplayName("두번 MISS 인 경우 테스트")
    void miss_2() {
        int playTimes = 2;
        RollSubject subject = new RollSubject(() -> Roll.of(1));
        PlayContext context = new PlayContext(subject);
        for (int frameNo = 1; frameNo <= playTimes; frameNo++) {
            context.play(frameNo);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(1, 1, 1, 1));
    }

    @Test
    @DisplayName("10번 MISS 인 경우 테스트")
    void miss_10() {
        int playTimes = 10;
        RollSubject subject = new RollSubject(() -> Roll.of(1));
        PlayContext context = new PlayContext(subject);
        for (int frameNo = 1; frameNo <= playTimes; frameNo++) {
            context.play(frameNo);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
    }

    @Test
    @DisplayName("100번 MISS 인 경우 테스트")
    void miss_100() {
        int playTimes = 100;
        RollSubject subject = new RollSubject(() -> Roll.of(1));
        PlayContext context = new PlayContext(subject);
        for (int frameNo = 1; frameNo <= playTimes; frameNo++) {
            context.play(frameNo);
        }
        assertThat(toRolls(context))
                .isEqualTo(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
    }

    private List<Integer> toRolls(PlayContext context) {
        return context.exportRollsDto()
                .getRolls()
                .stream()
                .map(RollDto::getCountOfPins)
                .collect(toList());
    }
}
