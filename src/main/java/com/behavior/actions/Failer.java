package com.behavior.actions;


import com.behavior.constant.B3Status;
import com.behavior.core.Action;
import com.behavior.core.Tick;


/**
 * 失败行为节点
 */
public class Failer extends Action {

	@Override
	public B3Status onTick(Tick tick) {
		return B3Status.FAILURE;
	}

}
