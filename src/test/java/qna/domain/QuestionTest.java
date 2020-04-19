package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("글 작성자과 사용자인지 테스트")
    @Test
    void testArticleOwn() throws CannotDeleteException {
        assertThatThrownBy(() -> {
            Q1.isOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
