package com.information.platform.tree;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.information.platform.utils.StringUtil;

import java.util.*;

import org.apache.shiro.util.CollectionUtils;

/**
 * 把一个list集合,里面的bean含有 pid 转为树形式
 *
 */
public class TreeUtil<T extends TreeEntity<T>> {
    /**
     * 判断两个父ID是否相同
     * @param p1
     * @param p2
     * @return
     */
    private boolean isEqualsPid(Object p1,Object p2){
    	return (p1.toString()).equals(p2.toString());
    }
    
    /**
     * 根据父节点的ID获取所有子节点，该方法顶级节点必须为空
     * @param list 分类表
     * @param pid 传入的父节点ID
     * @return List<T>
     */
    public List<T> getChildTree(List<T> list,Object pid) {
    	List<T> returnList = Lists.newArrayList();
        if(CollectionUtils.isEmpty(list)) {
            for (Iterator<T> iterator = list.iterator(); iterator.hasNext(); ) {
            	T t = (T) iterator.next();
                // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
                if (pid.equals(t.getPId())){
                    recursionFn(list, t);
                    returnList.add(t);
                }
            }
        }
        return returnList;
    }

    /**
     * 根据父节点的ID获取所有子节点，该方法顶级节点可以不为空,非树直接返回
     * @param list 分类表
     * @return List<T>
     */
    public List<T> getChildTree(List<T> list) {
        if(list!=null&&list.size()>0) {
            List<T> topList=Lists.newArrayList();
            List<T> subList=Lists.newArrayList();

            Map<String,String> idMap=Maps.newHashMap();

            for (Iterator<T> iterator = list.iterator(); iterator.hasNext(); ) {
                //归并所有list的id集合
            	T t = (T) iterator.next();
                idMap.put(t.getId().toString(), t.getId().toString());
            }

            for (Iterator<T> iterator = list.iterator(); iterator.hasNext(); ) {
                //获取最顶级的list
            	T t = (T) iterator.next();
            	System.out.println(t.getPId());
                if(t.getPId()==null|| StringUtil.isBlank(t.getPId().toString())){
                    topList.add(t);
                }else{
                    String id=idMap.get(t.getPId().toString());
                    if(StringUtil.isBlank(id)){
                        topList.add(t);
                    }else{
                        subList.add(t);
                    }
                }
            }
            if(topList!=null&&topList.size()>0&&subList!=null&&subList.size()>0){
            	System.out.println("1111");
                List<T> resultList=Lists.newArrayList();
                for (T t:topList) {
                    //将儿子级别的list归并到顶级中
                    List<T> subOneList=Lists.newArrayList();
                    for (T sub:subList) {
                        // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
                        if (isEqualsPid(sub.getPId(), t.getId())) {
                            recursionFn(subList, sub);
                            subOneList.add(sub);
                        }
                    }
                    System.out.println(subOneList.size());
                    t.setChildren(subOneList);
                    resultList.add(t);
                }
                return resultList;
            }else{
                return list;
            }
        }
        return list;
    }

    
    /**
     * 递归列表
     * @param list
     * @param t
     */
    private void  recursionFn(List<T> list, T t) {
        List<T> childList = getChildList(list, t);// 得到子节点列表
        t.setChildren(childList);
        for (T tChild : childList) {
        	recursionFn(list, tChild);
            if (hasChild(list, tChild)) {// 判断是否有子节点
                returnList.add(t);
                Iterator<T> it = childList.iterator();
                while (it.hasNext()) {
                	T n = (T) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    
    // 得到子节点列表
    public List<T> getChildList(List<T> list, T t) {
    	
    	List<T> tlist = Lists.newArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
        	T n = (T) it.next();
            if (n.getPId().equals(t.getId())) {
                tlist.add(n);
            }
        }
        
        return tlist;
        
    } 
    
    List<T> returnList = new ArrayList<T>();
    List<Integer> listStr = new ArrayList<Integer>();
    /**
     * 根据父节点的ID获取所有子节点
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<Integer> getChildTreeObjects(List<T> list, Object parentId){
        if(list == null) return null;
        for (Iterator<T> iterator = list.iterator(); iterator.hasNext();) {
        	T node = (T) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (parentId.equals(node.getPId())) {
            	getChildTreeObjects(list,node.getPId());
            	listStr.add(node.getPId());
            }
            // 二、遍历所有的父节点下的所有子节点
            /*if (node.getpPid()==0) {
                recursionFn(list, node);
            }*/
        }
        return listStr;
    }
    /**
     * 根据父节点的ID获取所有子节点
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    List<T> listStrs = new ArrayList<T>();
    public List<T> getChildTreeObject(List<T> list, Object parentId){
        if(list == null) return null;
        for (Iterator<T> iterator = list.iterator(); iterator.hasNext();) {
        	T node = (T) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (parentId.equals(node.getPId())) {
            	getChildTreeObjects(list,node.getPId());
            	listStrs.add(node);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*if (node.getpPid()==0) {
                recursionFn(list, node);
            }*/
        }
        return listStrs;
    }
//    private void recursionFn(List<T> list, T node,String p) {
//        List<T> childList = getChildList(list, node);// 得到子节点列表
//        if (hasChild(list, node)) {// 判断是否有子节点
//            returnList.add(node);
//            Iterator<T> it = childList.iterator();
//            while (it.hasNext()) {
//            	T n = (T) it.next();
//                //n.setName(p+n.getName());
//                recursionFn(list, n,p+p);
//            }
//        } else {
//            returnList.add(node);
//        }
//    }

    // 判断是否有子节点
    private boolean hasChild(List<T> list, T t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
    
}
