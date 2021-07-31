package com.behavior.core;


import com.behavior.constant.Const;


/**
 * 装饰节点
 */
public abstract class Decorator extends BaseNode implements IDecorator {

	private BaseNode child;

	@Override
	public void setChild(BaseNode child) {

		this.child = child;
	}

	@Override
	public BaseNode getChild() {
		return this.child;
	}

	@Override
	public String getCategory() {
		return Const.DECORATOR;
	}

}
