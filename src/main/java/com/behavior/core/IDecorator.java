package com.behavior.core;


/**
 * 装饰节点
 */
public interface IDecorator {

	/** 添加子节点 */
	void setChild(BaseNode child);

	/** 获取子节点 */
	BaseNode getChild();

}
