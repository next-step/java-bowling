package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("question 삭제 테스트")
    void deleteTest() throws CannotDeleteException {
        assertThat(Q1.delete(UserTest.JAVAJIGI))
                .containsAnyOf(new DeleteHistory(ContentType.QUESTION, Q1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    @DisplayName("question 삭제 실패 테스트")
    void deleteFailedTest() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
