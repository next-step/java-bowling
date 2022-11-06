package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final Question Q3 = new Question(1L, "title3", "contents2").writeBy(UserTest.BADA);

    @Test
    @DisplayName("질문 삭제 성공")
    void test() throws CannotDeleteException {
        // given
        List<DeleteHistory> deleteHistory = Q3.delete(UserTest.BADA);
        // when
        // then
        assertThat(deleteHistory).containsExactly(new DeleteHistory(ContentType.QUESTION, 1L, UserTest.BADA));
    }

    @Test
    @DisplayName("질문 삭제 실패")
    void test2() {
        // given
        // when
        // then
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.BADA);
        }).isInstanceOf(CannotDeleteException.class);
    }

}
