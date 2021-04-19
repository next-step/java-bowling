package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    private Question question;
    private Answer answer;
    private Answer answer2;

    @BeforeEach
    void setUp() {
        question = new Question("title1", "contents1").writeBy(JAVAJIGI);
        answer = new Answer(JAVAJIGI, question, "Answers Contents1");
        answer2 = new Answer(UserTest.SANJIGI, question, "Answers Contents2");
    }

    @Test
    void delete() throws CannotDeleteException {
        //given
        question.addAnswer(answer);
        //when
        question.delete(JAVAJIGI);
        //then
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문을 삭제할때, 작성자가 아닌 유저라면 exception")
    void checkOwner_fail() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> question.delete(SANJIGI))
            .withMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문을 삭제할때, 답변자와 질문자가 다르면, exception")
    void checkOwner_answer_fail() {
        //given
        question.addAnswer(answer2);
        //when
        //then
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> question.delete(JAVAJIGI))
            .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
