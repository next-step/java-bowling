package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import qna.CannotDeleteException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static qna.Fixture.*;

@DisplayName("질문 테스트")
@RunWith(MockitoJUnitRunner.class)
public class QuestionTest {
    @Mock
    private QuestionRepository questionRepository;
    private Question question;
    private Answer answer;

    @Before
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1").writeBy(JAVAJIGI);
        answer = new Answer(11L, JAVAJIGI, Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @DisplayName("질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태")
    @Test
    public void deleteFlag() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));
        questionRepository.findByIdAndDeletedFalse(question.getId());

        assertThat(question.isDeleted()).isFalse();

        question.delete(JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능")
    @Test
    public void deleteByOther() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));
        questionRepository.findByIdAndDeletedFalse(question.getId());

        assertThatThrownBy(() -> question.delete(SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }


    @DisplayName("답변이 없는 경우 삭제가 가능")
    @Test
    public void deleteWhenThereIsNoAnswer() {
        Question questionWithoutAnswer = new Question(2L, "title1", "contents1").writeBy(JAVAJIGI);

        when(questionRepository.findByIdAndDeletedFalse(questionWithoutAnswer.getId())).thenReturn(Optional.of(questionWithoutAnswer));
        questionRepository.findByIdAndDeletedFalse(questionWithoutAnswer.getId());

        questionWithoutAnswer.delete(JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 답변글의 모든 답변자 같은 경우 삭제가 가능")
    @Test
    public void deleteByOwner() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));
        questionRepository.findByIdAndDeletedFalse(question.getId());

        question.delete(JAVAJIGI);

        question.getAnswers().forEach(answer -> assertThat(answer.isOwner(JAVAJIGI)).isTrue());
    }

    @DisplayName("질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태")
    @Test
    public void deleteAnswerFlag() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));
        questionRepository.findByIdAndDeletedFalse(question.getId());

        question.delete(JAVAJIGI);

        question.getAnswers().forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    @DisplayName("질문자와 답변자가 다른경우 답변을 삭제할 수 없다")
    @Test
    public void deleteQuestionWithAnswerWrittenByOther() {
        Answer answerWrittenByOther = new Answer(12L, SANJIGI, Q1, "Answers Contents2");

        question.addAnswer(answerWrittenByOther);
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));
        questionRepository.findByIdAndDeletedFalse(question.getId());

        assertThatThrownBy(() -> question.delete(JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

}
