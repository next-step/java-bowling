package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotCreatedDeleteHistories;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest extends DomainBuilder{

    @ParameterizedTest
    @MethodSource("createQuestionInstanceHasNotAnswers")
    public void delete_작성자와_동일하며_답변이없을때_삭제(Question question, User loginUser) {
        //Given
        question.delete(loginUser);

        //Then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void delete_질문자와_답변자가_같을경우() {
        //Given
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Question question = new Question(1L, "title", "content", Arrays.asList(answer1, answer2)).writeBy(UserTest.JAVAJIGI);

        //When
        question.delete(question.getWriter());

        //Then
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();

    }

    @Test
    public void delete_다른_사람의_답변이_있을경우() {
        assertThatThrownBy(() -> {
            Question question = buildQuestionAndAnotherPeopleAnswers();
            question.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @ParameterizedTest
    @MethodSource("createQuestionFailCaseInstance")
    public void delete_다른_사람이_쓴_글(Question question, User loginUser) {
        assertThatThrownBy(() ->
                question.delete(loginUser)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @ParameterizedTest
    @MethodSource("createQuestionInstanceHasNotAnswers")
    public void canNotDeleteHistories(Question question) {
        assertThatThrownBy(() ->
             question.createDeleteHistories()
        ).isInstanceOf(CannotCreatedDeleteHistories.class);
    }

    @DisplayName("질문 중 답변이 있는 글을 삭제 후 삭제 히스토리가 생성되는지 확인")
    @Test
    public void createDeleteHistoriesHasAnswers() {
        //Given
        Question question = buildQuestionAndMyselfAnswers();

        //When
        question.delete(question.getWriter());
        List<DeleteHistory> histories = question.createDeleteHistories();

        assertThat(histories).hasSize(3);
    }

    @DisplayName("질문 중 답변이 없는 글을 삭제 후 삭제 히스토리가 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("createQuestionInstanceHasNotAnswers")
    public void createDeleteHistoriesHasNotAnswers(Question question) {
        //Given & When
        question.delete(question.getWriter());
        List<DeleteHistory> histories = question.createDeleteHistories();

        assertThat(histories).hasSize(1);
    }
}
