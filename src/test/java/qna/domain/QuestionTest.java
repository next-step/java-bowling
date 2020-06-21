package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Question 클래스 테스트")
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("Question을 삭제할 수 있다.")
    @Test
    void delete_success() throws CannotDeleteException {
        DeleteHistories deleteHistories = Q1.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistories.getDeleteHistories().get(0))
                .isEqualTo(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
    }

    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 예외를 반환한다.")
    @Test
    void delete_fail() {
        assertThatThrownBy(() -> Q2.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
