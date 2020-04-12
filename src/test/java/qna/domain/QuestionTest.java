package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("본인이 작성한 글이 아닌 글을 삭제할 경우 에러 발생")
    void errorOnDeleteOthersQuestion() {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
