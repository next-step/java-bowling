package qna.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import qna.CannotDeleteException;
import qna.domain.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QnaServiceTest {
	@Mock
	private QuestionRepository questionRepository;

	@Mock
	private DeleteHistoryService deleteHistoryService;

	@InjectMocks
	private QnAService qnAService;

	private Question question;
	private Answer answer;
	private Question sangjigi;


	@Before
	public void setUp() throws Exception {
		question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
		sangjigi = new Question(2L, "title1", "contents1").writeBy(UserTest.SANJIGI);
		answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
		question.addAnswer(answer);
		sangjigi.addAnswer(answer);
	}

	@Test
	public void delete_성공() throws Exception {
		when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

		assertThat(question.isDeleted()).isFalse();
		qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

		assertThat(question.isDeleted()).isTrue();
		verifyDeleteHistories();
	}

	@Test
	public void delete_다른_사람이_쓴_글() throws Exception {
		when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

		assertThatThrownBy(() -> {
			qnAService.deleteQuestion(UserTest.SANJIGI, question.getId());
		}).isInstanceOf(CannotDeleteException.class)
			.hasMessage("질문을 삭제할 권한이 없습니다.");
	}

	@Test
	public void delete_성공_질문자_답변자_같음() throws Exception {
		when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

		qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

		assertThat(question.isDeleted()).isTrue();
		assertThat(answer.isDeleted()).isTrue();
		verifyDeleteHistories();
	}

	@Test
	public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
		when(questionRepository.findByIdAndDeletedFalse(sangjigi.getId())).thenReturn(Optional.of(sangjigi));

		assertThatThrownBy(() -> {
			qnAService.deleteQuestion(UserTest.SANJIGI, sangjigi.getId());
		}).isInstanceOf(CannotDeleteException.class)
			.hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
	}

	private void verifyDeleteHistories() {
		List<DeleteHistory> deleteHistories = Arrays.asList(
			new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
			new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
		verify(deleteHistoryService).saveAll(deleteHistories);
	}
}
