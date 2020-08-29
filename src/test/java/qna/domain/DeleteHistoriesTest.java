package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.fixture.UserFixture;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeleteHistoriesTest {
    @DisplayName("빈 history가 잘 생성되는지 확인")
    @Test
    public void emptyHistories() {
        // when & then
        assertAll(
                () -> assertThat(DeleteHistories.emptyHistories().getClass()).isEqualTo(DeleteHistories.class),
                () -> assertThat(DeleteHistories.emptyHistories().toList()).hasSize(0)
        );
    }

    @DisplayName("history record 가 잘 생성 되는지 확인")
    @Test
    public void recordTest() {
        // given
        User user = UserFixture.make(1L, "userId", "Password", "Name", "Email");
        DeleteHistory history = DeleteHistory.of(ContentType.QUESTION, 1L, user);

        // when
        DeleteHistories histories = DeleteHistories.emptyHistories()
                .record(history);

        // then
        assertAll(
                () -> assertThat(histories.toList()).hasSize(1),
                () -> assertThat(histories.toList().contains(history)).isTrue()
        );
    }

    @DisplayName("history records 가 잘 생성 되는지 확인")
    @Test
    public void recordAllTest() {
        // given
        User user = UserFixture.make(1L, "userId", "Password", "Name", "Email");
        DeleteHistory questionHistory = DeleteHistory.of(ContentType.QUESTION, 1L, user);
        DeleteHistory answerHistory = DeleteHistory.of(ContentType.ANSWER, 1L, user);
        List<DeleteHistory> history = Arrays.asList(questionHistory, answerHistory);

        // when
        DeleteHistories histories = DeleteHistories.emptyHistories()
                .recordAll(history);

        // then
        assertAll(
                () -> assertThat(histories.toList()).hasSize(2),
                () -> assertThat(histories.toList().contains(questionHistory)).isTrue(),
                () -> assertThat(histories.toList().contains(answerHistory)).isTrue()
        );
    }
}
