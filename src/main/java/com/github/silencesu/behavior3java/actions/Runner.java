package com.github.silencesu.behavior3java.actions;


import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Tick;
import com.github.silencesu.behavior3java.core.Action;


/**
 * 允许行为节点
 */
public class Runner extends Action {

	@Override
	public B3Status onTick(Tick tick) {
		return B3Status.SUCCESS;
	}

}
