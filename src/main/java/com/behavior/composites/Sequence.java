package com.behavior.composites;


import com.behavior.constant.B3Status;
import com.behavior.core.Tick;
import com.behavior.core.Composite;


/**
 * 顺序节点 （与节点）
 */
public class Sequence extends Composite {

	@Override
	public B3Status onTick(Tick tick) {
		for (int i = 0; i < this.getChildCount(); i++) {
			B3Status status = this.getChild(i).execute(tick);
			if (status != B3Status.SUCCESS) {
				return status;
			}
		}
		return B3Status.SUCCESS;
	}

}
