package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertTrue;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("답변이 없는 경우 삭제 가능")
    public void 답변_없는_경우() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        assertTrue(question.hasAnswer());
    }

    @Test
    @DisplayName("질문을 삭제할 권한이 없는경우")
    public void 질문삭제_권한_없는_경우() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        assertThatThrownBy(() -> {
            question.validatePermission(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문 삭제")
    public void 질문_삭제() throws CannotDeleteException {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.delete(UserTest.JAVAJIGI);
        assertTrue(question.isDeleted());
    }
}
