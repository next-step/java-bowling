package qna.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static qna.domain.User.GUEST_USER;

import org.junit.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void 작성자가_미로그인() {
        assertThatThrownBy(() -> {
            Q2.validateDeleted(GUEST_USER);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 같은_작성자_작성자_답변글_미존재() throws CannotDeleteException {
        Question question = new Question("title3", "contents3").writeBy(UserTest.JAVAJIGI);
        assertThat(question.validateDeleted(UserTest.JAVAJIGI)).isTrue();
    }

    @Test
    public void 다른_작성자_답변글_미존재_CannotDeleteException() {
        assertThatThrownBy(() -> {
            Q1.validateDeleted(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 같은_작성자_같은_답변글() throws CannotDeleteException {
        Question question = new Question("title3", "contents3").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.A1);
        assertThat(question.validateDeleted(UserTest.JAVAJIGI)).isTrue();
    }

    @Test
    public void 같은_작성자_다른_답변글_CannotDeleteException() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> {
            Q1.validateDeleted(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 같은_작성자_같은_답변글_포함_다른_답변글_CannotDeleteException() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> {
            Q1.validateDeleted(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 삭제_이력() {
        System.out.println(Q1.getDeleteHistories());
    }
}
