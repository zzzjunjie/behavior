package com.behavior.composites;


import com.behavior.annotation.BehaviorNode;
import com.behavior.constant.B3Status;
import com.behavior.core.Composite;
import com.behavior.core.Tick;
import com.behavior.enums.NodeTypeEnums;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * 顺序节点 （与节点）
 */
@Component
@Scope("prototype")
@BehaviorNode(TYPE_ENUMS = NodeTypeEnums.COMPOSITES)
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
