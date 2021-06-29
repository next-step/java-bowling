package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {

    public static final Question Q1 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    Question question;

    @BeforeEach
    void setUp() {
        question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("질문 삭제 가능 테스트 - 질문 작성자와 동일")
    void deletableQuestion() {

        question.deleteQuestion(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 삭제 가능 테스트 - 질문 작성자와 모든 답변자가 동일")
    void deletableQuestionWithAnswer() {
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        question.addAnswer(answer);

        question.deleteQuestion(UserTest.JAVAJIGI);

        assertAll(
                () -> assertThat(question.isDeleted()).isTrue(),
                () -> assertThat(answer.isDeleted()).isTrue()
        );
    }


    @Test
    @DisplayName("질문 삭제 불가 테스트 - 질문 작성자와 다름")
    void nonDeletableQuestion() {

        assertThatThrownBy(() -> question.deleteQuestion(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.QUESTION_DELETE_PERMISSION);
    }

    @Test
    @DisplayName("질문 삭제 불가 테스트 - 질문 작성자와 다른 답변 작성자 존재")
    void nonDeletableQuestionWithAnswer() {
        Answer answer = new Answer(UserTest.SANJIGI, question, "Answers Contents1");

        question.addAnswer(answer);

        assertThatThrownBy(() -> question.deleteQuestion(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.QUESTION_WITH_OTHERS_ANSWER);
    }

}
