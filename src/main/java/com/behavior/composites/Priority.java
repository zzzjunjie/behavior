package com.behavior.composites;


import com.behavior.annotation.BehaviorNode;
import com.behavior.constant.B3Status;
import com.behavior.core.Composite;
import com.behavior.core.Tick;
import com.behavior.enums.NodeTypeEnums;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * 选择节点 （或节点）
 */
@Component
@Scope("prototype")
@BehaviorNode(TYPE_ENUMS = NodeTypeEnums.COMPOSITES)
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
