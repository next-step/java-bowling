package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private User questionOwnerUser;
    private User otherUser;

    @BeforeEach
    void setUp() {
        questionOwnerUser = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        otherUser = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    }

    @Test
    @DisplayName("login 유저가 본인 소유가 아닌 질문을 삭제하려 할 때의 예외 처리")
    void testQuestionsOwnerValidation() {
        Question sampleQuestion = new Question("title", "content").writeBy(questionOwnerUser);

        assertThatThrownBy(
                () -> sampleQuestion.delete(otherUser)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문글에 자신의 소유가 아닌 답변글이 포함되어 있을 시 예외 처리")
    void testQuestionsAnswerValidation() {
        Question questionThatHaveOtherUsersAnswer = new Question("title", "content").writeBy(questionOwnerUser);

        questionThatHaveOtherUsersAnswer.addAnswer(
                new Answer(questionOwnerUser, questionThatHaveOtherUsersAnswer, "Answers Contents1"));
        questionThatHaveOtherUsersAnswer.addAnswer(
                new Answer(otherUser, questionThatHaveOtherUsersAnswer, "Answers Contents2"));

        assertThatThrownBy(
                () -> questionThatHaveOtherUsersAnswer.delete(questionOwnerUser)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
