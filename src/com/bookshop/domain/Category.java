package com.bookshop.domain;

import java.util.Set;

public class Category {
	private String cid;// 主键
	private String cname;// 分类名称
	private Category parent;// 父分类
	private String desc;// 分类描述
	private String pid;//用于查询
	private Integer orderBy;
	private Set<Category> children;// 子分类

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", parent="
				+ parent + ", desc=" + desc +"]";
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

}
