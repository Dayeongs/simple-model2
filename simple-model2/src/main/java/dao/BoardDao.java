package dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ibatis.IbatisUtil;
import vo.Board;

public class BoardDao {
	
	private final SqlMapClient ibatis = IbatisUtil.getSqlMapClient();
	
	public List<Board> getAllBoards() throws SQLException {
		return (List<Board>) ibatis.queryForList("boards.getAllBoards");
	}
	public Board getBoardByNo(int no) throws SQLException {
		return (Board) ibatis.queryForList("boards.getBoardByNo");
	}
	public void insertBoard(Board board) throws SQLException {
		ibatis.insert("boards.insertBoard", board);
	}
	public void updateBoard(Board board) throws SQLException {
		ibatis.update("boards.updateBoard", board);
	}

}
