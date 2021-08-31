package qna.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    @Test
    void deleteAnswer() {
        // Given
        Answer givenA1 = AnswerTest.A1;
        Answers answers = new Answers();
        answers.add(givenA1);
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.addDeleteHistory(new DeleteHistory(ContentType.ANSWER, givenA1.getId(), givenA1.writer(), LocalDateTime.now()));

        // When
        answers.deleteAnswer(deleteHistories);

        // Then
        assertThat(answers.isAnswerEmptyByLoginUser(AnswerTest.A1.writer())).isTrue();
    }
}