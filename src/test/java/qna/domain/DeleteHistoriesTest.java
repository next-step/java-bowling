package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.QuestionTest.Q1;

class DeleteHistoriesTest {
    public static final DeleteHistory DELETE_HISTORY1 = new DeleteHistory(ContentType.QUESTION, 1L, Q1.getWriter(),
            LocalDateTime.now());

    public static final DeleteHistories DELETE_HISTORIES1 = new DeleteHistories(List.of(DELETE_HISTORY1));

    public static final DeleteHistories DELETE_HISTORIES2 = new DeleteHistories();

    @Test
    @DisplayName("Delete 히스토리를 추가한다.")
    public void add_히스토리를_추가한다() {
        int questionId = 1;

        DELETE_HISTORIES2.add(questionId, Q1);

        assertThat(DELETE_HISTORIES2.getHistories()).isEqualTo(DELETE_HISTORIES1.getHistories());
    }
}
