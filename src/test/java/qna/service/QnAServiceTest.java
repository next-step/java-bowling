package qna.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.CannotDeleteException;
import qna.domain.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QnAServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @DisplayName("다른사람이 쓴 글을 삭제하려고하면 익셉션")
    @Test
    public void delete_다른_사람이_쓴_글() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> qnAService.deleteQuestion(UserTest.SANJIGI, question.getId()))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("다른사람이 쓴 댓글이 있는 글을 삭제하려고하면 익셉션")
    @Test
    public void delete_다른_사람이_쓴_댓글() {
        Answer anotherAnswer = new Answer(11L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1 ");
        question.addAnswer(new Answer(11L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1"));
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> qnAService.deleteQuestion(UserTest.SANJIGI, question.getId()))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("게시글 정상삭제 테스트")
    @Test
    public void delete_성공_질문자_답변자_같음() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

}