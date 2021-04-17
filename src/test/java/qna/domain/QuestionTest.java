package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("Question 삭제할때 작성자가 다르면 예외가 발생한다")
    void checkPossibleDelete() {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("Question 삭제하면 deleted 가  true 로 변경된다")
    void delete() {
        // given
        Q1.delete(UserTest.JAVAJIGI);

        // when
        boolean isDeleted = Q1.isDeleted();

        // then
        assertThat(isDeleted).isTrue();
    }

    @Test
    @DisplayName("Question 삭제하면 deleteHistory 생성된다")
    void deleteHistory() {
        // given
        DeleteHistory deleteHistory = Q1.delete(UserTest.JAVAJIGI);

        // when
        DeleteHistory expectedDeleteHistory =
                new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now());

        // then
        assertThat(deleteHistory).isEqualTo(expectedDeleteHistory);
    }
}
