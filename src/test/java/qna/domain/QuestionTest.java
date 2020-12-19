package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void delete_by_writer() {
        Q1.deleteBy(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_by_other_Exception() {
        assertThatThrownBy(Q1.deleteBy(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_with_answers_by_writer() {
        Q1.addAnswer(AnswerTest.A1);

        Q1.deleteBy(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_with_answers_by_other_Exception() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(Q1.deleteBy(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
