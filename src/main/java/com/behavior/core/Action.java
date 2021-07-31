package com.behavior.core;


import com.behavior.constant.Const;


/**
 * 行为节点  叶节点
 */
public abstract class Action extends BaseNode implements IAction {

	@Override
	public String getCategory() {
		return Const.ACTION;
	}

}
