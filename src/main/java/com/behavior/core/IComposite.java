package com.behavior.core;


/**
 * 组合节点
 */
public interface IComposite {

	/** 子节点数目 */
	int getChildCount();

	/** 根据id 索引 子节点 */
	BaseNode getChild(int index);

	/** 增加一个子节点 */
	void addChild(BaseNode baseNode);

}
