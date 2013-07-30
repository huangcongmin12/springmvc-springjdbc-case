package com.utils;

public class Page {
	private int pageNow = 1;		// 当前页数
	private int pageSize = 22;		// 每页显示多少条记录
	private int totalPage;			// 共有多少页
	private int totalSize;			// 一共多少记录
	@SuppressWarnings("unused")
	private boolean hasFirst;		// 是否有首页
	@SuppressWarnings("unused")
	private boolean hasPre;			// 是否有前一页
	@SuppressWarnings("unused")
	private boolean hasNext;		// 是否有下一页
	@SuppressWarnings("unused")
	private boolean hasLast;		// 是否有最后一页

	public Page(int pageNow, int pageSize, int totalSize) {
		// 利用够照函数为变量赋值
		this.pageNow = pageNow;
		this.pageSize = pageSize;
		this.totalSize = totalSize;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		// 一共多少页的算法
		totalPage = getTotalSize() / getPageSize();
		if (totalSize % pageSize != 0)
			totalPage++;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public boolean isHasFirst() {
		// 如果当前为第一页就没有首页
		if (pageNow == 1)
			return false;
		else
			return true;
	}

	public void setHasFirst(boolean hasFirst) {
		this.hasFirst = hasFirst;
	}

	public boolean isHasPre() {
		// 如果有首页就有前一页，因为有首页就不是第一页
		if (this.isHasFirst())
			return true;
		else
			return false;
	}

	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}

	public boolean isHasNext() {
		// 如果有尾页就有下一页，因为有尾页表明不是最后一页
		if (isHasLast())
			return true;
		else
			return false;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isHasLast() {
		// 如果不是最后一页就有尾页
		if (pageNow == this.getTotalPage())
			return false;
		else
			return true;
	}

	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}

}
