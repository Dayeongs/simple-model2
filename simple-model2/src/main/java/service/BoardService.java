package service;

import java.util.List;

import dao.BoardDao;
import vo.Board;

public class BoardService {

	private final BoardDao boardDao = new BoardDao();
	
	public List<Board> getBoardList() throws Exception {
		return boardDao.getAllBoards(); 
	}

}
