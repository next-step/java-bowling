package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("Question 삭제 테스트")
    @Test
    public void deleteQuestionTest() {
        assertThatCode(AnswerTest.A1.deleteByUser(UserTest.JAVAJIGI)).doesNotThrowAnyException();

        assertThatThrownBy(AnswerTest.A2.deleteByUser(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
