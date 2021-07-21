package com.github.silencesu.behavior3java.core;


import com.github.silencesu.behavior3java.constant.B3Const;


/**
 * 行为节点  叶节点
 */
public abstract class Action extends BaseNode implements IAction {

	@Override
	public String getCategory() {
		return B3Const.ACTION;
	}

}
