package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.ContentType.ANSWER;
import static qna.domain.QuestionTest.Q1;
import static qna.domain.UserTest.JAVAJIGI;

class AnswersTest {

    @Test
    void delete() throws CannotDeleteException {
        Answers answers = new Answers();
        Answer answer1 = new Answer(JAVAJIGI, Q1, "content");
        Answer answer2 = new Answer(JAVAJIGI, Q1, "content");
        answers.add(answer1);
        answers.add(answer2);

        DeleteHistories deleteHistories = answers.delete(JAVAJIGI);

        assertThat(deleteHistories.contains(DeleteHistory.of(ANSWER, answer1.getId(), JAVAJIGI))).isTrue();
        assertThat(deleteHistories.contains(DeleteHistory.of(ANSWER, answer2.getId(), JAVAJIGI))).isTrue();
    }

}
