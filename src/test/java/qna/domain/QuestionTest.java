package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question("title", "contents").writeBy(UserTest.JAVAJIGI);
    }

    @DisplayName("질문 작성자와 작성자와 로그인 사용자가 일치하면, 질문을 삭제할 수 있다.")
    @Test
    void deletable() throws Exception {
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문 작성자와 로그인 사용자가 일치하지 않으면, 질문 삭제 시 CannotDeleteException 예외가 발생한다.")
    @Test
    void notDeletable() {
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문에 답변이 있는 경우, 질문자와 모든 답변 글의 답변자가 같은 경우에만 질문을 삭제할 수 있다.")
    @Test
    void deletableQuestionWithAnswers() throws Exception {
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "테스트1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "테스트2"));

        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문에 답변이 있는 경우, 질문자와 답변자가 다르면 질문을 삭제할 수 없다.")
    @Test
    void notDeletableQuestionWithAnswers() {
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "답변1"));
        question.addAnswer(new Answer(UserTest.SANJIGI, question, "답변2"));

        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

}
