package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {

    public Question question;
    public User loginUser;
    public User guestUser;

    @BeforeEach
    void setUp() {
        question = new Question("How to learn java?", "I want to be a java master", UserTest.JAVAJIGI);
        Answer A1 = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        Answer A2 = new Answer(UserTest.JAVAJIGI, question, "Answers Contents2");
        question.addAnswer(A1);
        question.addAnswer(A2);
        loginUser = UserTest.JAVAJIGI;
        guestUser = User.GUEST_USER;
    }

    @DisplayName("질문을 삭제할 때 답변자가 자기 자신이 아닌 경우 삭제할 수 없다.")
    @Test
    void 질문_삭제시_답변자가_자신이_아니면_삭제할_수_없다() {
        assertThatThrownBy(
            () -> question.delete(guestUser)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 삭제가 성공하면 답변 삭제 히스토리를 포함한 질문 삭제 히스토리를 반환한다.")
    @Test
    void 질문_삭제시_답변_삭제와_함께_기록이_반환된다() throws CannotDeleteException {
        List<DeleteHistory> deleteHistoryList = question.delete(loginUser);
        assertAll(
            () -> assertThat(deleteHistoryList).isInstanceOf(List.class),
            () -> assertThat(deleteHistoryList.get(0)).isInstanceOf(DeleteHistory.class)
        );
    }
}
