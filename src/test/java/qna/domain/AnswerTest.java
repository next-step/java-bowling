package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    public void deleted_by_writer() {
        A1.deleteBy(UserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void delete_by_other_Exception() {
        assertThatThrownBy(A1.deleteBy(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_cascaded_from_question() {
        QuestionTest.Q1.addAnswer(A1);
        QuestionTest.Q1.deleteBy(UserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }

}
