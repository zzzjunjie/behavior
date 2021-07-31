package com.behavior.actions;


import com.behavior.constant.B3Status;
import com.behavior.core.Action;
import com.behavior.core.Tick;


/**
 * 允许行为节点
 */
public class Runner extends Action {

	@Override
	public B3Status onTick(Tick tick) {
		return B3Status.SUCCESS;
	}

}
