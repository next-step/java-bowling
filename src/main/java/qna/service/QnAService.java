package qna.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.domain.AnswerRepository;
import qna.domain.DeleteHistory;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.User;
import qna.domain.UserRepository;

@Service
@Transactional
public class QnAService {
	private static final Logger log = LoggerFactory.getLogger(QnAService.class);

	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	private final DeleteHistoryService deleteHistoryService;
	private final UserRepository userRepository;

	public QnAService(QuestionRepository questionRepository, AnswerRepository answerRepository,
		DeleteHistoryService deleteHistoryService, UserRepository userRepository) {
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
		this.deleteHistoryService = deleteHistoryService;
		this.userRepository = userRepository;
	}

	@Transactional(readOnly = true)
	public Question findQuestionById(Long id) {
		return questionRepository.findByIdAndDeletedFalse(id)
			.orElseThrow(NotFoundException::new);
	}

	public void deleteQuestion(User loginUser, long questionId) throws CannotDeleteException {
		Question question = findQuestionById(questionId);
		List<DeleteHistory> deleteHistories = question.delete(loginUser);
		deleteHistoryService.saveAll(deleteHistories);
	}

	public Question saveQuestion(Question question) {
		questionRepository.save(question);
		return question;
	}

	public User addUser(User loginUser) {
		userRepository.save(loginUser);
		return loginUser;
	}

	public User getUserByUserId(String userId) {
		return userRepository.findByUserId(userId)
			.orElseThrow(() -> new IllegalArgumentException("no such user found"));
	}
}
