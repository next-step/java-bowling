package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteHistoriesTest {

    @Test
    void addDeleteHistory() {
        DeleteHistories deleteHistories = new DeleteHistories();
        Question question = QuestionTest.Q1;

        deleteHistories.addDeleteHistory(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter()));
        Assertions.assertThat(deleteHistories.getDeleteHistory(0))
                .isEqualTo(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter()));
    }
}
