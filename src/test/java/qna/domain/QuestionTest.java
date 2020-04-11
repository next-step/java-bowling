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
    @DisplayName("삭제여부 테스트 성공")
    void 삭제여부_테스트_성공() throws CannotDeleteException {
        assertThat(Q1.delete(UserTest.JAVAJIGI))
                .containsAnyOf(new DeleteHistory(ContentType.QUESTION, Q1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    @DisplayName("삭제여부 테스트 실패")
    void 삭제여부_테스트_실패() {
        assertThatThrownBy(
                () -> Q1.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }


}
