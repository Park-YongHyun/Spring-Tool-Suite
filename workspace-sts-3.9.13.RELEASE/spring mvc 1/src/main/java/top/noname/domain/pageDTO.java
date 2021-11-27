package top.noname.domain;

public class pageDTO {
	private int pageNum, pageSize;

	private int startRow, endRow;

	private int startPage, endPage, totalPage;
	private boolean prev, next;

	private String searchKeyword, searchType;

	public pageDTO() {
		this(1, 10);
	}
	public pageDTO(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		setPageSize(pageSize);
	}

	private void setRows() {
		startRow = ((pageNum - 1) * pageSize) + 1;
		endRow = pageNum * pageSize;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;

		setRows();
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize > 100 ? 100 : pageSize;

		setRows();
	}
	public void setPages(int count) {
		totalPage = (int) Math.ceil((double) count / pageSize);
		startPage = (pageNum - 1) / 10 * 10 + 1;
		endPage = startPage + 9;
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		prev = pageNum > 10;
		next = endPage < totalPage;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public int getPageNum() {
		return pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getStartRow() {
		return startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public boolean isNext() {
		return next;
	}
	public boolean isPrev() {
		return prev;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public String getSearchType() {
		return searchType;
	}

	@Override
	public String toString() {
		return "pageDTO [pageNum=" + pageNum + ", pageSize=" + pageSize + ", startRow=" + startRow + ", endRow="
				+ endRow + ", startPage=" + startPage + ", endPage=" + endPage + ", totalPage=" + totalPage + ", prev="
				+ prev + ", next=" + next + ", searchKeyword=" + searchKeyword + ", searchType=" + searchType + "]";
	}
}
