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
    void 원소추가시_새로운_객체_반환() {
        DeleteHistories deleteHistories1 = new DeleteHistories();
        DeleteHistories deleteHistories2 = deleteHistories1.add(DH1);

        assertThat(deleteHistories1.histories()).hasSize(0);
        assertThat(deleteHistories2.histories()).hasSize(1);
        assertThat(deleteHistories1).isNotSameAs(deleteHistories2);
    }
}