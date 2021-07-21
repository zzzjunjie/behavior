package com.github.silencesu.behavior3java.composites;


import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Tick;
import com.github.silencesu.behavior3java.core.Composite;


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
