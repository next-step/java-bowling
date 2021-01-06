package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import qna.NotFoundException;
import qna.UnAuthorizedException;
import qna.service.DeleteHistoryService;
import qna.service.QnAService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@RunWith(MockitoJUnitRunner.class)
public class AnswerTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    private Question question;
    private Answer answer;

    @Before
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    public void check_null_작성자(){
        assertThatThrownBy(() -> {
            Answer answerTest = new Answer(1L, null, question, "test");
        }).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    public void check_null_question(){
        assertThatThrownBy(() -> {
            Answer answerTest = new Answer(1L,  UserTest.JAVAJIGI, null, "test");
        }).isInstanceOf(NotFoundException.class);
    }
}
