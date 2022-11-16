package qna.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {

    private static final DeleteHistory DH1 = new DeleteHistory(
            ContentType.QUESTION,
            QuestionTest.Q1.getId(),
            QuestionTest.Q1.getWriter(),
            LocalDateTime.now()
    );

    @Test
    void add() {
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(DH1);

        assertThat(deleteHistories.histories()).containsExactly(DH1);
    }
}