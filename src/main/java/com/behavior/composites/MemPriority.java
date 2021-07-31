package com.behavior.composites;


import com.behavior.annotation.BehaviorNode;
import com.behavior.constant.Const;
import com.behavior.constant.B3Status;
import com.behavior.core.Composite;
import com.behavior.core.Tick;
import com.behavior.enums.NodeTypeEnums;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * 记忆选择节点 （或节点）
 */
@Component
@Scope("prototype")
@BehaviorNode(TYPE_ENUMS = NodeTypeEnums.COMPOSITES)
public class MemPriority extends Composite {

	@Override
	public void onOpen(Tick tick) {
		super.onOpen(tick);
		tick.getBlackboard().setParam(Const.RUNNING_CHILD, 0, tick.getTree().getId(), this.getId());
	}

	@Override
	public B3Status onTick(Tick tick) {

		Integer childId = tick.getBlackboard().getParam(Const.RUNNING_CHILD, tick.getTree().getId(), this.getId());

		for (int i = childId; i < this.getChildCount(); i++) {
			B3Status status = this.getChild(i).execute(tick);

			if (status != B3Status.FAILURE) {

				if (status == B3Status.RUNNING) {
					tick.getBlackboard().setParam(Const.RUNNING_CHILD, i, tick.getTree().getId(), this.getId());
				}

				return status;
			}
		}

		return B3Status.FAILURE;
	}

}
