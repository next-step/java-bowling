package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    Question question = new Question();

    @Test
    public void 사용자_질문자_동일한경우() {
        User loginUser = new User();
        question.writeBy(loginUser);
        assertThatThrownBy(() -> question.deleteOnlyIsOwner(loginUser)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 질문자가아닌_답변글_있을경우() throws CannotDeleteException {
        User loginUser = new User();
        Answer answer = new Answer(1L, loginUser, question, "");
        question.addAnswer(answer);

        assertThat(question.answersByOwner(loginUser).get(0).getId()).isEqualTo(1L);

        User notLoginUser = new User(2L, "2", "1234", "notLoginUser", "");
        assertThatThrownBy(() -> question.answersByOwner(notLoginUser)).isInstanceOf(CannotDeleteException.class);
    }
}
