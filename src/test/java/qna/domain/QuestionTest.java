package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void deleteQuestionTest_성공() throws CannotDeleteException {
        DeleteHistories deleteHistories = Q1.deleteHistories(UserTest.JAVAJIGI);
        assertThat(deleteHistories.getDeleteHistories().get(0))
                .isEqualTo(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
    }

    @Test
    void deleteQuestionTest_실패() {
        assertThatThrownBy(() -> Q2.deleteHistories(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
