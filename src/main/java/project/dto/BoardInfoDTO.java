package project.dto;

import java.sql.Date;

public class BoardInfoDTO {
	
	private int boardId;
	private String boardTitle;
	private String boardDesc;
	private String boardUser;
	private String boardComment;
	private String boardPassword;
	
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardDesc() {
		return boardDesc;
	}
	public void setBoardDesc(String boardDesc) {
		this.boardDesc = boardDesc;
	}
	public String getBoardUser() {
		return boardUser;
	}
	public void setBoardUser(String boardUser) {
		this.boardUser = boardUser;
	}
	public String getBoardComment() {
		return boardComment;
	}
	public void setBoardComment(String boardComment) {
		this.boardComment = boardComment;
	}
	public String getBoardPassword() {
		return boardPassword;
	}
	public void setBoardPassword(String boardPassword) {
		this.boardPassword = boardPassword;
	}
	
	
	
	
}
