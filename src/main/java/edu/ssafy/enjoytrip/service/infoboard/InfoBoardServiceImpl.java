package edu.ssafy.enjoytrip.service.infoboard;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ssafy.enjoytrip.dto.infoboard.InfoBoardDto;
import edu.ssafy.enjoytrip.repository.infoboard.InfoBoardRepository;
import edu.ssafy.enjoytrip.util.SizeConstant;

@Service
public class InfoBoardServiceImpl implements InfoBoardService {
	InfoBoardRepository infoBoardRepository;
	
	public InfoBoardServiceImpl(InfoBoardRepository infoBoardRepository) {
		this.infoBoardRepository = infoBoardRepository;
	}

	@Override
	public void modify(InfoBoardDto infoBoardDto) {
		infoBoardRepository.modify(infoBoardDto);
	}

	@Override
	public InfoBoardDto select(int infoBoardId) {
		return infoBoardRepository.select(infoBoardId);
	}

	@Override
	public List<InfoBoardDto> list(@RequestParam Map<String, String> map) {
		String key = map.get("key");
		String word = map.get("word");
		map.put("key", "".equals(key) ? null : key);
		map.put("word", "".equals(word) ? null : word);
		
		int pageNum = map.get("pgno") != null ? Integer.valueOf(map.get("pgno")) : 1;
		map.put("size", String.valueOf(SizeConstant.LIST_SIZE));
		map.put("offset", String.valueOf(SizeConstant.LIST_SIZE * (pageNum - 1)));
		
		return infoBoardRepository.list(map);
	}

	@Override
	public void delete(int infoBoardId) {
		infoBoardRepository.delete(infoBoardId);
	}

	@Override
	public void write(InfoBoardDto infoBoardDto) {
		infoBoardRepository.write(infoBoardDto);
	}

	@Override
	public void updateHit(int infoBoardId) {
		infoBoardRepository.updateHit(infoBoardId);
	}

	@Override
	public int totalCount(Map<String, String> map) {
		return infoBoardRepository.totalCount(map);
	}
	
}
