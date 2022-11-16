package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("delete 메소드는 질문을 삭제한다.")
    void delete() {
        List<DeleteHistory> histories = Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(histories).containsOnly(new DeleteHistory(ContentType.QUESTION, Q1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    @DisplayName("User 객체가 누락된 경우 질문을 삭제하는데 실패한다.")
    void delete_with_null() {
        assertThatThrownBy(() -> Q1.delete(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("입력 값이 누락되었습니다.");
    }

    @Test
    @DisplayName("질문 작성자와 삭제 주체가 다른 경우 질문을 삭제하는데 실패한다.")
    void delete_with_not_equal_writer() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
