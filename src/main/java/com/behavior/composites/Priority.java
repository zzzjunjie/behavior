package com.behavior.composites;


import com.behavior.constant.B3Status;
import com.behavior.core.Composite;
import com.behavior.core.Tick;


/**
 * 选择节点 （或节点）
 */
public class Priority extends Composite {

	@Override
	public B3Status onTick(Tick tick) {
		for (int i = 0; i < this.getChildCount(); i++) {
			B3Status status = this.getChild(i).execute(tick);
			if (status != B3Status.FAILURE) {
				return status;
			}
		}
		return B3Status.FAILURE;
	}

}
