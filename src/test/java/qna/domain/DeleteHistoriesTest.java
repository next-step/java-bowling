package qna.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class DeleteHistoriesTest {
    public static DeleteHistories HISTORIES_H2 = DeleteHistories.of(List.of(DeleteHistoryTest.H2));
    public static DeleteHistories HISTORIES_H1H2 = DeleteHistories.of(List.of(DeleteHistoryTest.H1, DeleteHistoryTest.H2));
    @Test
    public void create() {
        assertThat(DeleteHistories.of(List.of(DeleteHistoryTest.H1, DeleteHistoryTest.H2)))
                .isEqualTo(DeleteHistories.of(List.of(DeleteHistoryTest.H1, DeleteHistoryTest.H2)));
    }

    @ParameterizedTest(name = "create failed")
    @NullSource
    public void createFailed(List<DeleteHistory> deleteHistories) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> DeleteHistories.of(deleteHistories))
                .withMessageContaining("cannot be null");
    }

    @Test
    public void collect() {
        assertThat(DeleteHistories.of(List.of(DeleteHistoryTest.H1, DeleteHistoryTest.H2)).collect())
                .isEqualTo(List.of(DeleteHistoryTest.H1, DeleteHistoryTest.H2));
    }
    @Test
    public void prepend() {
        assertThat(HISTORIES_H2.prepend(DeleteHistoryTest.H1)).isEqualTo(HISTORIES_H1H2);
    }

    @ParameterizedTest(name = "append failed: {arguments}")
    @NullSource
    public void prependFailed(DeleteHistory deleteHistory) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> HISTORIES_H2.prepend(deleteHistory))
                .withMessageContaining("cannot be null");
    }
}
