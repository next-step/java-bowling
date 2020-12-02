package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SubjectTest {

    private final Subject<Integer> subject = new Subject<Integer>() {
        private int num = 0;

        @Override
        Integer get() {
            return num;
        }

        @Override
        void execute() {
            num++;
            notifyObservers();
        }
    };

    @Test
    @DisplayName("옵저버 패턴에서 옵저버 등록 테스트")
    void register_observer() {
        List<Integer> events = new LinkedList<>();
        subject.register(subject -> events.add(subject.get()));

        subject.execute();
        subject.execute();
        subject.execute();
        subject.execute();
        subject.execute();

        assertThat(events)
                .isEqualTo(Arrays.asList(1, 2, 3, 4, 5));
    }

    @ParameterizedTest
    @DisplayName("runnable 등록 테스트")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void register_runnable(int size) {
        List<Integer> events = new LinkedList<>();
        subject.register(() -> events.add(0));

        for (int i = 0; i < size; i++) {
            subject.execute();
        }

        assertThat(events.size())
                .isEqualTo(size);
    }
}
