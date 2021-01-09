package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import qna.CannotDeleteException;
import qna.service.DeleteHistoryService;
import qna.service.QnAService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class AnswersTest {
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

    @ParameterizedTest
    @NullSource
    public void add_Answer_Null_체크(Answers answers) {
        assertThatThrownBy(() -> {
            answers.addAnswer(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    public void answer_다른_사람_답변_Null_체크(User loginUser) {
        Answers answers = new Answers();
        assertThatThrownBy(() -> {
            answers.isOtherAnswer(loginUser);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void 다른_사람의_쓴글_확인(){
        assertThatThrownBy(() -> {
            question.isOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
