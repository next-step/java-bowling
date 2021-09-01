package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @Test
    void create() {
        assertThat(Answers.from(AnswerTest.A1, AnswerTest.A2)).isEqualTo(Answers.from(AnswerTest.A1, AnswerTest.A2));
    }

    @Test
    void delete() throws CannotDeleteException {
        assertThat(Answers.from(AnswerTest.A1, AnswerTest.A1).delete(UserTest.JAVAJIGI))
                .isInstanceOf(ArrayList.class);
    }
}
