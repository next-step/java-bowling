package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void delete_성공() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);

        assertThatCode(() -> Q1.delete(UserTest.JAVAJIGI, UserTest.JAVAJIGI.getId()))
                .doesNotThrowAnyException();
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_성공_답변이_없는_경우() {
        assertThatCode(() -> Q1.delete(UserTest.JAVAJIGI, UserTest.JAVAJIGI.getId()))
                .doesNotThrowAnyException();
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_불가_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> {
            Q1.checkQuestionDeletable(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_불가_다른_사람이_쓴_답변_존재() {
        Q2.addAnswer(AnswerTest.A1);

        assertThatThrownBy(() -> {
            Q2.checkAnswerDeletable(Q2.getWriter());
        }).isInstanceOf(CannotDeleteException.class);
    }
}
