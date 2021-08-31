package qna.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    @Test
    void deleteAnswer() {
        // Given
        Answer givenA1 = AnswerTest.A1;
        Answers answers = new Answers();
        answers.add(givenA1);
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, givenA1.getId(), givenA1.getWriter(), LocalDateTime.now()));

        // When
        answers.deleteAnswer(deleteHistories);

        // Then
        assertThat(answers.isAnswerEmptyByLoginUser(AnswerTest.A1.getWriter())).isTrue();
    }
}