package com.information.platform.bean;

import com.information.platform.tree.TreeEntity;

public class Permission extends TreeEntity<Permission>{
    
    private Integer id;
    
    //接口权限
    private String code;
    
    //权限名
    private String description;
    
    //父节点id
    private Integer pId;
    
    //导航地址
    private String url;

    //图标
   private  Integer iconId;
   
   //权限类型(0:导航权限,1:数据权限)
   private Integer style;
   
   //导航标识码
   private String navCode;
   
   //排序
   private Integer orderId;
   
   //是否被选中
   private boolean checked;
    
    public boolean isChecked() {
	return checked;
}

public void setChecked(boolean checked) {
	this.checked = checked;
}

	public Integer getStyle() {
	return style;
}

public void setStyle(Integer style) {
	this.style = style;
}

	public Integer getIconId() {
	return iconId;
}

public void setIconId(Integer iconId) {
	this.iconId = iconId;
}

	
    
    
    public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public Integer getPId() {
		return pId;
	}

	public void setPId(Integer pId) {
		this.pId = pId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}