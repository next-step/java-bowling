package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.SANJIGI;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);


    @Test
    void questionNotWiter() throws CannotDeleteException {
        Question question = Q1;

        assertThatThrownBy(
                () -> question.delete(SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);

    }
}
