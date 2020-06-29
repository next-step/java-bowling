package qna.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import qna.CannotDeleteException;
import qna.domain.Answer;
import qna.domain.DeleteHistory;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.UserRepository;
import qna.domain.UserTest;

@SpringBootTest
@Transactional
public class QnaServiceTest {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private DeleteHistoryService deleteHistoryService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QnAService qnAService;

	private Question question;
	private Answer answer;

	@BeforeEach
	public void setUp() throws Exception {
		qnAService.addUser(UserTest.JAVAJIGI);
		qnAService.addUser(UserTest.SANJIGI);
		deleteHistoryService.init();
		question = new Question("title1", "contents1", qnAService.getUserByUserId("javajigi"));
		answer = new Answer(qnAService.getUserByUserId("javajigi"), question, "Answers Contents1");
		question.addAnswer(answer);
		qnAService.saveQuestion(question);
	}

	@Test
	void 질문이_저장된다() {
		assertThat(questionRepository.findAll()).isNotEmpty();
	}

	@Test
	public void delete_성공() throws Exception {
		assertThat(question.isDeleted()).isFalse();
		qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

		assertThat(question.isDeleted()).isTrue();
		assertAll(
			() -> assertThat(deleteHistoryService.getAll()).isInstanceOf(List.class),
			() -> assertThat(deleteHistoryService.getAll().get(0)).isInstanceOf(DeleteHistory.class)
		);
	}

	@Test
	public void delete_다른_사람이_쓴_글() throws Exception {
		assertThatThrownBy(() -> {
			qnAService.deleteQuestion(UserTest.SANJIGI, question.getId());
		}).isInstanceOf(CannotDeleteException.class);
	}

	@Test
	public void delete_성공_질문자_답변자_같음() throws Exception {
		qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());
		assertThat(question.isDeleted()).isTrue();
		assertThat(answer.isDeleted()).isTrue();
		assertAll(
			() -> assertThat(deleteHistoryService.getAll()).isInstanceOf(List.class),
			() -> assertThat(deleteHistoryService.getAll().get(0)).isInstanceOf(DeleteHistory.class)
		);
	}

	@Test
	public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
		assertThatThrownBy(() -> {
			qnAService.deleteQuestion(UserTest.SANJIGI, question.getId());
		}).isInstanceOf(CannotDeleteException.class);
	}
}
