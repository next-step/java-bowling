package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;

class AnswersTest {
    public static final Answers AS1 = new Answers(Arrays.asList(A1, A2));
    public static final Answers AS2 = new Answers(Arrays.asList(A1, A1));

    @Test
    @DisplayName("Answers.delete validation throw exception")
    void validate(){
        assertThatThrownBy(()->AS1.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}