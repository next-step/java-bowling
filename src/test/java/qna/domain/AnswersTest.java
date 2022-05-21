package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    @Test
    void add() {
        Answers answers = new Answers();
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answers.add(answer);
        assertThat(answers).isEqualTo(new Answers(Arrays.asList(answer)));
    }

    @Test
    void checkPrivilegeOnAnswer() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answers answers = new Answers(Arrays.asList(answer));
        assertThatThrownBy(() -> answers.checkPrivilegeOnAnswer(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void deleteHistory() {
        Answer actualAnswer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answers answers = new Answers(Arrays.asList(actualAnswer));
        DeleteHistories actualDeleteHistories = answers.deleteHistory();
        DeleteHistories expectedDeleteHistories = new DeleteHistories(Arrays.asList(new DeleteHistory(ContentType.ANSWER, actualAnswer.getId(), actualAnswer.getWriter(), LocalDateTime.now())));
        assertThat(actualAnswer.isDeleted()).isTrue();
        assertThat(actualDeleteHistories).isEqualTo(expectedDeleteHistories);
    }
}
