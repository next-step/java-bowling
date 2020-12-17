package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @DisplayName("정상적으로 질문이 삭제 마킹이 되는지에 대한 테스트(답변 없음)")
    void testNoAnswerQuestionDelete() throws CannotDeleteException {
        Question questionThatHasNoAnswer = new Question("title", "content").writeBy(questionOwnerUser);

        questionThatHasNoAnswer.delete(questionOwnerUser);

        assertThat(questionThatHasNoAnswer.isDeleted())
                .isTrue();
    }

    @Test
    @DisplayName("정상적으로 질문이 삭제 마킹 되는지에 대한 테스트(답변이 모두 질문자의 답변일 때)")
    void testQuestionDelete() throws CannotDeleteException {
        Question questionThatHaveAnswersOwnerWrite = new Question("title", "content").writeBy(questionOwnerUser);

        questionThatHaveAnswersOwnerWrite.addAnswer(
                new Answer(questionOwnerUser, questionThatHaveAnswersOwnerWrite, "Answers Contents1"));
        questionThatHaveAnswersOwnerWrite.addAnswer(
                new Answer(questionOwnerUser, questionThatHaveAnswersOwnerWrite, "Answers Contents2"));

        questionThatHaveAnswersOwnerWrite.delete(questionOwnerUser);

        assertThat(questionThatHaveAnswersOwnerWrite.isDeleted())
                .isTrue();
    }

    @Test
    @DisplayName("답변이 모두 질문자의 답변일 때 답변 모두 정상적으로 삭제되는 지 확인")
    void testAnswerDelete() throws CannotDeleteException {
        Question sampleQuestion = new Question("title", "content").writeBy(questionOwnerUser);

        List<Answer> answers = Arrays.asList(
                new Answer(questionOwnerUser, sampleQuestion, "Answers Content1"),
                new Answer(questionOwnerUser, sampleQuestion, "Answers Content2"),
                new Answer(questionOwnerUser, sampleQuestion, "Answers Content3"),
                new Answer(questionOwnerUser, sampleQuestion, "Answers Content4")
        );

        sampleQuestion.delete(questionOwnerUser);

        for (Answer answer : answers) {
            assertThat(answer.isDeleted()).isTrue();
        }
    }

    @Test
    @DisplayName("Question 이 삭제된 모든 질문과 답변을 정상적으로 반환하는지 테스트")
    void testDeletedAll() throws CannotDeleteException {
        Question sampleQuestion = new Question(1L, "title", "content").writeBy(questionOwnerUser);

        List<Answer> answers = Arrays.asList(
                new Answer(1L, questionOwnerUser, sampleQuestion, "Answers Content1"),
                new Answer(2L, questionOwnerUser, sampleQuestion, "Answers Content2"),
                new Answer(3L, questionOwnerUser, sampleQuestion, "Answers Content3"),
                new Answer(4L, questionOwnerUser, sampleQuestion, "Answers Content4")
        );

        answers.forEach(sampleQuestion::addAnswer);

        // DeleteHistory 의 equals()는 createDate 를 이용하지 않는다.
        List<DeleteHistory> expected = new ArrayList<>();
        expected.add(new DeleteHistory(ContentType.QUESTION, 1L, questionOwnerUser, LocalDateTime.now()));
        expected.add(new DeleteHistory(ContentType.ANSWER, 1L, questionOwnerUser, LocalDateTime.now()));
        expected.add(new DeleteHistory(ContentType.ANSWER, 2L, questionOwnerUser, LocalDateTime.now()));
        expected.add(new DeleteHistory(ContentType.ANSWER, 3L, questionOwnerUser, LocalDateTime.now()));
        expected.add(new DeleteHistory(ContentType.ANSWER, 4L, questionOwnerUser, LocalDateTime.now()));

        List<DeleteHistory> histories = sampleQuestion.delete(questionOwnerUser);

        assertThat(histories).isEqualTo(expected);
    }
}
