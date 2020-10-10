package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 삭제 가능 여부 예외 테스트")
    @Test
    void shouldExceptionWriterNotEqualsLoginUser() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .withMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문 삭제 테스트")
    @Test
    void delete() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleteHistories.size()).isEqualTo(1);
    }
}
