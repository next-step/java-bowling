package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DeleteHistoriesTest {

    @Test
    @DisplayName("삭제 히스토리들 생성 테스트")
    void createDeleteHistoriesTest() {
        Question question = QuestionTest.Q1;
        question.addAnswer(AnswerTest.A1);
        Answer answer = question.getAnswers().get(0);
        List<DeleteHistory> deleteHistories = new DeleteHistories(question).getDeleteHistories();

        assertAll(
                () -> assertThat(
                        deleteHistories.get(0)
                ).isEqualTo(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now())),

                () -> assertThat(
                        deleteHistories.get(1)
                ).isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
        );
    }
}
