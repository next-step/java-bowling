package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("글 작성자과 사용자인지 테스트")
    @Test
    void testArticleOwn() throws CannotDeleteException {
        assertThatThrownBy(() -> {
            A1.isOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
