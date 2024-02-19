package service;

import java.util.List;
import java.util.Map;

import dao.BoardDao;
import dto.BoardListDto;
import dto.Pagination;
import vo.Board;

public class BoardService {

	private final BoardDao boardDao = new BoardDao();
	
	// 반환타입: BoardListDto (pagination, boards를 담는 객체)
	public BoardListDto getBoardList(int currentPage, Map<String, Object> param) throws Exception {
		int totalRows = boardDao.getTotalRows();
		Pagination pagination = new Pagination(currentPage, totalRows);
		param.put("begin", pagination.getBegin());
		param.put("end", pagination.getEnd());
		
		List<Board> boards = boardDao.getAllBoards(param);
		
		BoardListDto dto = new BoardListDto();
		dto.setItems(boards);
		dto.setPagination(pagination);
		
		return dto;
	}

	public void save(Board board) throws Exception {
		boardDao.insertBoard(board);	
	}

	public Board getBoardDetail(int no) throws Exception {
		// 상세정보를 조회해서 조회수 증가시키기
		Board board = boardDao.getBoardByNo(no);
		board.setReadCount(board.getReadCount() + 1);
		boardDao.updateBoard(board);
		
		return boardDao.getBoardByNo(no);
	}

	// 저장, 변경, 삭제는 항상 반환타입이 void이다.
	public void deleteBoard(int no) throws Exception {
		Board board = boardDao.getBoardByNo(no);
		board.setDeleted("Y");
		boardDao.updateBoard(board);
	}
	
	public void update(int no, String title, String content) throws Exception {
		Board board = boardDao.getBoardByNo(no);
		board.setTitle(title);
		board.setContent(content);
		boardDao.updateBoard(board);
	}

}
