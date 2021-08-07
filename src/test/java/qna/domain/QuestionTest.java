package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import qna.CannotDeleteException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

@RunWith(MockitoJUnitRunner.class)
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);

    @Mock
    private QuestionRepository questionRepository;

    @DisplayName("로그인 사용자와 질문한 사용자가 같으면 삭제가 가능하다")
    @Test
    public void should_delete_same_user() {
        //arrange
        Question question = new Question(100L, "title", "contents").writeBy(JAVAJIGI);
        Answer answer = new Answer(1L, JAVAJIGI, Q1, "Answers Contents1");
        question.addAnswer(answer);
        given(questionRepository.findByIdAndDeletedFalse(question.getId())).willReturn(Optional.of(question));
        questionRepository.findByIdAndDeletedFalse(question.getId());

        //act
        List<DeleteHistory> deleteHistories = question.delete(JAVAJIGI);

        //assert
        assertTrue(deleteHistories.size() > 0);
    }


    @DisplayName("답변이 없는 경우 삭제할 수 있다.")
    @Test
    public void should_delete_when_no_answer() {
        //arrange
        Question question = new Question(1L, "title", "contents").writeBy(JAVAJIGI);
        given(questionRepository.findByIdAndDeletedFalse(question.getId())).willReturn(Optional.of(question));
        questionRepository.findByIdAndDeletedFalse(question.getId());

        //act
        question.delete(JAVAJIGI);

        //assert
        assertTrue(question.isDeleted());
    }

    @DisplayName("질문과 답변의 작성자가 같으면 삭제 가능")
    @Test
    public void should_delete_same_writer() {
        //arrange
        Question question = new Question(100L, "title", "contents").writeBy(JAVAJIGI);
        Answer answer = new Answer(1L, JAVAJIGI, Q1, "Answers Contents1");
        question.addAnswer(answer);
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));
        questionRepository.findByIdAndDeletedFalse(question.getId());

        //act
        question.delete(JAVAJIGI);

        //assert
        question.getAnswers().forEach(an -> assertThat(an.isOwner(JAVAJIGI)).isTrue());
    }

    @DisplayName("질문과 답변의 작성자가 다르면 삭제 할 수 없다")
    @Test
    public void should_not_delete_different_writer() {
        //arrange
        Answer answerWrittenByOther = new Answer(3L, SANJIGI, Q1, "Answers Contents2");
        Question question = new Question(100L, "title", "contents").writeBy(JAVAJIGI);
        question.addAnswer(answerWrittenByOther);
        given(questionRepository.findByIdAndDeletedFalse(question.getId())).willReturn(Optional.of(question));

        //act
        questionRepository.findByIdAndDeletedFalse(question.getId());

        //assert
        assertThatThrownBy(() -> question.delete(JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

}
