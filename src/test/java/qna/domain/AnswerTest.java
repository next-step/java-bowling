package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(12L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    public static Answer ofUser(Long id, User answer_user) {
       return new Answer(id, answer_user, QuestionTest.Q1, "답글 : ^^");
    }

    @Test
    void delete_answer_성공() throws CannotDeleteException {
        Answer answer = ofUser(11L, UserTest.JAVAJIGI);
        new Answer(answer).deleteAnswer(UserTest.JAVAJIGI);
    }

    @Test
    void delete_답변자가_다를_경우() throws CannotDeleteException {
        Answer answer = ofUser(11L, UserTest.JAVAJIGI);
        assertThatThrownBy(() -> new Answer(answer).deleteAnswer(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
