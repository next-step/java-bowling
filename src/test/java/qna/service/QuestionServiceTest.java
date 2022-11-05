package qna.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.CannotDeleteException;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.UserTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    private QuestionService questionService;

    private Question question;
    private Question deletedQuestion;

    @BeforeEach
    public void setup() {
        questionService = new QuestionService(questionRepository);
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        deletedQuestion = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        deletedQuestion.setDeleted(true);
    }

    @Test
    @Order(1)
    public void 질문삭제_성공() throws CannotDeleteException {
        when(questionRepository.save(question)).thenReturn(deletedQuestion);
        assertThat(questionService.delete(question, UserTest.JAVAJIGI)).isEqualTo(deletedQuestion);
    }

    @Test
    @Order(2)
    public void 질문삭제_실패_질문삭제권한없음() {
        assertThatThrownBy(
                () -> questionService.delete(question, UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }
}
