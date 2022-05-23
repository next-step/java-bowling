package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static qna.domain.AnswerTest.a1;
import static qna.domain.AnswerTest.a2;

public class QuestionTest {
    public static final Question Q1 = q1();
    public static final Question Q2 = q2();

    public static Question q1() {
        return new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    public static Question q2() {
        return new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    }

    @Test
    void delete는_질문자와_사용자가_같은_경우_삭제가_가능하다() {
        Question question = q1();

        question.delete(UserTest.JAVAJIGI);

        assertTrue(question.isDeleted());
    }

    @Test
    void delete는_질문자와_사용자가_다른_경우_삭제가_불가능하다() {
        Question question = q1();

        assertThatThrownBy(() -> {
            question.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete는_답변이_없는_경우_삭제가_가능하다() {
        Question question = q1();

        question.delete(UserTest.JAVAJIGI);

        assertTrue(question.isDeleted());
    }

    @Test
    void delete는_질문자_답변자와_사용자가_같은_경우_삭제가_가능하다() {
        Question question = q1();
        Answer answer = a1();
        question.addAnswer(answer);

        question.delete(UserTest.JAVAJIGI);

        assertTrue(question.isDeleted());
        assertTrue(answer.isDeleted());
    }

    @Test
    void delete는_답변자와_사용자가_다른_경우_삭제가_불가능하다() {
        Question question = q1();
        question.addAnswer(a2());

        assertThatThrownBy(() -> {
            question.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
