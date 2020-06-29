package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {

    public Question question;
    public User guestUser;

    @BeforeEach
    void setUp() {
        question = new Question("How to learn java?", "I want to be a java master", UserTest.JAVAJIGI);
        Answer A1 = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        Answer A2 = new Answer(UserTest.SANJIGI, question, "Answers Contents2");
        question.addAnswer(A1);
        question.addAnswer(A2);
        guestUser = User.GUEST_USER;
    }

    @DisplayName("질문을 삭제할 때 답변자가 자기 자신이 아닌 경우 삭제할 수 없다.")
    @Test
    void 질문_삭제시_답변자가_자신이_아니면_삭제할_수_없다() {
        assertThatThrownBy(
            () -> question.delete(guestUser)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
