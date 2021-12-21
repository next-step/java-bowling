package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("질문 테스트")
public class QuestionTest {
    public static final Question Q1 = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question(2L, "title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 삭제")
    @Test
    void delete() throws CannotDeleteException {
        // given
        Question question = new Question("t1", "c1").writeBy(UserTest.JAVAJIGI);
        // when
        question.delete(UserTest.JAVAJIGI);
        // then
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문 삭제시 예외 확인 - 본인이 작성한 질문이 아닌 경우")
    @Test
    void deleteFailed() {
        // given
        Question question = new Question("t1", "c1").writeBy(UserTest.SANJIGI);
        // when & then
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .withMessageContaining("질문을 삭제할 권한이 없습니다.");
    }
}
