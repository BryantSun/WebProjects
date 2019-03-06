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
public abstract class TreeEntity<T>  {
	private static final long serialVersionUID = 7225882681055881835L;
	@Transient
	protected Integer pId;
	@Transient
	protected Integer id;

	@Transient
	protected List<T> children=Lists.newArrayList();		// 根据用户ID查询角色列表
	
	public TreeEntity() {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getPId() {
		return pId;
	}

	public void setPId(Integer pId) {
		this.pId = pId;
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
