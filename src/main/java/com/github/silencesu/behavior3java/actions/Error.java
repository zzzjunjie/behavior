package com.github.silencesu.behavior3java.actions;


import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Action;
import com.github.silencesu.behavior3java.core.Tick;


/**
 * 出错行为节点
 */
public class Error extends Action {

	@Override
	public B3Status onTick(Tick tick) {
		return B3Status.ERROR;
	}

}
