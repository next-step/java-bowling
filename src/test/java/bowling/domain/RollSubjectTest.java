package bowling.domain;

import bowling.dto.RollDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class RollSubjectTest {
    private int num = 0;
    private final RollSubject subject = new RollSubject(() -> Roll.of(num++));

    @Test
    @DisplayName("exportRollDto 테스트")
    void exportRollDto() {
        subject.execute();
        subject.execute();
        subject.execute();
        subject.execute();
        subject.executeZero();
        subject.executeZero();

        assertThat(subject.exportRollsDto()
                .getRolls()
                .stream()
                .map(RollDto::getCountOfPins)
                .collect(toList())
        ).isEqualTo(Arrays.asList(0, 1, 2, 3, 0, 0));
    }

    @Test
    @DisplayName("get 테스트")
    void get() {
        subject.execute();
        subject.execute();
        subject.execute();
        subject.execute();
        subject.executeZero();
        subject.executeZero();

        assertThat(subject.get()
                .exportRollsDto()
                .getRolls()
                .stream()
                .map(RollDto::getCountOfPins)
                .collect(toList())
        ).isEqualTo(Arrays.asList(0, 1, 2, 3, 0, 0));
    }
}
