package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;
import qna.ExistTheOthersAnswerException;
import qna.NotHaveDeleteOwnerShipException;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question1;
    private Question question2;

    @BeforeEach
    void init(){
        question1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question2 =  new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    }

    @ParameterizedTest
    @DisplayName("질문 삭제 권한 테스트 - 질문 작성자가 아닐때 삭제시 예외처리")
    @MethodSource("provideQuestionAndWriter")
    void validQuestionTest(Question question, User writer){
        assertThatThrownBy(() -> question.delete(writer))
                    .isInstanceOf(NotHaveDeleteOwnerShipException.class)
                    .hasMessage(NotHaveDeleteOwnerShipException.NO_OWNERSHIP);
    }

    private static Stream<Arguments> provideQuestionAndWriter(){
        return Stream.of(
                Arguments.of(Q1, UserTest.SANJIGI),
                Arguments.of(Q2, UserTest.JAVAJIGI)
        );
    }

    @Test
    @DisplayName("질문 삭제 권한 테스트 - 질문 작성자는 맞지만 답변자중 타인의 답변글이 있을 경우 예외처리")
    void validQuestionTest2(){
        provideQuestionAndWriterTheOtherCase();
        assertThatThrownBy(() -> question1.delete(UserTest.JAVAJIGI))
                .isInstanceOf(ExistTheOthersAnswerException.class)
                .hasMessage(ExistTheOthersAnswerException.EXIST_OTHERS_ANSWER);

        assertThatThrownBy(() -> question2.delete(UserTest.SANJIGI))
                .isInstanceOf(ExistTheOthersAnswerException.class)
                .hasMessage(ExistTheOthersAnswerException.EXIST_OTHERS_ANSWER);
    }

    private void provideQuestionAndWriterTheOtherCase(){
        question1.addAnswer(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        question1.addAnswer(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));
        question2.addAnswer(new Answer(UserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents1"));
        question2.addAnswer(new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents2"));
    }

    @ParameterizedTest
    @DisplayName("질문 삭제 기능 테스트 - 답변 없는 경우")
    @MethodSource("provideQuestion")
    void deleteTest(Question question, User user) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = question.delete(user);
        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(1);
        assertThat(deleteHistories).extracting("contentType", ContentType.class).contains(ContentType.QUESTION);
    }

    private static Stream<Arguments> provideQuestion(){
        return Stream.of(
                Arguments.of(Q1, UserTest.JAVAJIGI),
                Arguments.of(Q2, UserTest.SANJIGI)
        );
    }

    @Test
    @DisplayName("질문 삭제 기능 테스트 - 답변 있는 경우[본인이 작성한 답변만 있을 때]")
    void deleteTest2() throws CannotDeleteException {
        provideQuestionExistAnswers();
        int answersSize1 = question1.getAnswersSize();
        List<DeleteHistory> deleteHistories1 = question1.delete(UserTest.JAVAJIGI);
        assertThat(question1.isDeleted()).isTrue();
        assertThat(deleteHistories1).hasSize(answersSize1 + 1); //질문수 + 답변수
        assertThat(deleteHistories1).extracting("contentType", ContentType.class).contains(ContentType.QUESTION);

        int answersSize2 = question2.getAnswersSize();
        List<DeleteHistory> deleteHistories2 = question2.delete(UserTest.SANJIGI);
        assertThat(question1.isDeleted()).isTrue();
        assertThat(deleteHistories2).hasSize(answersSize2 + 1); //질문수 + 변수
        assertThat(deleteHistories2).extracting("contentType", ContentType.class).contains(ContentType.QUESTION);
    }

    private void provideQuestionExistAnswers(){
        question1.addAnswer(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        question1.addAnswer(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));
        question2.addAnswer(new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents1"));
        question2.addAnswer(new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents2"));
    }
}
