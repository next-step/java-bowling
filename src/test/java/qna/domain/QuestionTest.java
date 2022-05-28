package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 내가_쓴글이_아닌_글을_삭제할때() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 작성자이면_질문을_삭제할_수_있다() throws CannotDeleteException {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer contents1");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer contents2");
        Q1.addAnswer(answer1);
        Q1.addAnswer(answer2);

        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void 답변이_질문자가_아닌_경우_삭제_불가능() {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer contents1");
        Q2.addAnswer(answer1);

        assertThatThrownBy(() -> Q2.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
