package com.information.platform.tree;


import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.google.common.collect.Lists;

/**
 * 树
 */
/**
 * @author Administrator
 *
 * @param <T>
 */
/**
 * @author Administrator
 *
 * @param <T>
 */
@MappedSuperclass
public abstract class TreeEntitys<T>  {
	private static final long serialVersionUID = 7225882681055881835L;
	@Transient
	protected Integer parentId;
	@Transient
	protected Integer id;

	@Transient
	protected List<T> children=Lists.newArrayList();		// 根据用户ID查询角色列表
	
	public TreeEntitys() {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	


	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<T> getChildren() {
		return children;
	}

	public void setChildren(List<T> children) {
		this.children = children;
	}

	
}
