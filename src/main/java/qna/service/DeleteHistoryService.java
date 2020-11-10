package qna.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qna.domain.DeleteHistory;
import qna.domain.DeleteHistoryRepository;

@Service("deleteHistoryService")
public class DeleteHistoryService {
	@Resource(name = "deleteHistoryRepository")
	private DeleteHistoryRepository deleteHistoryRepository;

	@Transactional
	public void saveAll(List<DeleteHistory> deleteHistories) {
		deleteHistoryRepository.saveAll(deleteHistories);
	}

	@Transactional
	public void save(DeleteHistory deleteHistory) {
		deleteHistoryRepository.save(deleteHistory);
	}

	@Transactional
	public List<DeleteHistory> getAll() {
		List<DeleteHistory> histories = StreamSupport.stream
			(deleteHistoryRepository.findAll().spliterator(), false)
			.collect(Collectors.toList());
		return histories;
	}

	@Transactional
	public void init() {
		deleteHistoryRepository.deleteAll();
	}
}
