package qna.domain;

import java.util.List;

import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void deleteTest() throws CannotDeleteException {

        User writer = new User("123", "456", "789", "123");

        Question question = new Question("t", "c").writeBy(writer);
        question.delete(writer);

        assertTrue(question.isDeleted());

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> question.delete(UserTest.JAVAJIGI));

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> Q1.delete(writer));
    }

    @Test
    void deleteServiceTest() throws CannotDeleteException {

        User writer = new User("123", "456", "789", "123");

        Question question = new Question("t", "c").writeBy(writer);
        question.delete(writer, this::dummy);

        assertTrue(question.isDeleted());
    }

    void dummy(Question question, List<Answer> answers) { }
}
