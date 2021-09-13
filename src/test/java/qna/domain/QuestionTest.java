package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;
    private Answer answer;

    @BeforeEach
    void setUp() {
        question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    @DisplayName("삭제 성공")
    void success_delete() throws CannotDeleteException {
        List<DeleteHistory> delete = question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();

        int questionIndex = delete.indexOf(question.createDeleteHistory());
        int answerIndex = delete.indexOf(answer.delete());
        assertThat(questionIndex).isNotEqualTo(-1);
        assertThat(answerIndex).isNotEqualTo(-1);
    }

    @Test
    @DisplayName("작성자와 로그인 유저가 다를경우 실패")
    void invalid_another_login_user() {
        assertThatThrownBy(() -> {
            question.deleteInvalid(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("작성자와 다른 사람의 답변이 있는지")
    void find_another_user_answer() {
        question.addAnswer(AnswerTest.A2);
        assertThat(question.existsAnotherUserAnswer()).isTrue();
    }

    @Test
    @DisplayName("답변이 작성자와 다를 경우 실패")
    void invalid_another_answer() {
        question.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> {
            question.deleteInvalid(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
