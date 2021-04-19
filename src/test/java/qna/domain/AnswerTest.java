package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Question question;
    private Answer answer;

    @BeforeEach
    void setUp() {
        question = new Question("title1", "contents1").writeBy(JAVAJIGI);
        answer = new Answer(JAVAJIGI, question, "Answers Contents1");
    }

    @Test
    void delete() throws CannotDeleteException {
        //given
        //when
        answer.delete(JAVAJIGI);
        //then
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제할 때 질문자와 답변자가 다르면, exception")
    void checkOwner_fail() {
        //given
        //when
        //then
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> answer.delete(SANJIGI))
            .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
