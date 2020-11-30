package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("옵저버 패턴 테스트")
    void observer_pattern() {
        List<Integer> events = new LinkedList<>();
        subject.register((Observer<Integer>) subject -> events.add(subject.get()));

        subject.execute();
        subject.execute();
        subject.execute();
        subject.execute();
        subject.execute();

        assertThat(events)
                .isEqualTo(Arrays.asList(1, 2, 3, 4, 5));
    }
}
