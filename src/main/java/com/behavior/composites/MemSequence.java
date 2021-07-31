package com.behavior.composites;


import com.behavior.annotation.BehaviorNode;
import com.behavior.constant.Const;
import com.behavior.constant.B3Status;
import com.behavior.core.Blackboard;
import com.behavior.core.Composite;
import com.behavior.core.Tick;
import com.behavior.enums.NodeTypeEnums;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * 记忆顺序节点 （与节点）
 */
@Component
@Scope("prototype")
@BehaviorNode(TYPE_ENUMS = NodeTypeEnums.COMPOSITES)
public class MemSequence extends Composite {

	@Override
	public void onOpen(Tick tick) {

		Blackboard.Memory memory = tick.getBlackboard().getMemory(tick.getTree().getId(), this.getId());
		memory.getMemeory().put(Const.RUNNING_CHILD, 0);
	}

	@Override
	public B3Status onTick(Tick tick) {

		Blackboard.Memory mm = tick.getBlackboard().getMemory(tick.getTree().getId(), this.getId());

		int child = (int) mm.getMemeory().get(Const.RUNNING_CHILD);

		int childCount = getChildCount();

		for (int i = child; i < childCount; i++) {
			B3Status status = this.getChild(i).execute(tick);

			if (status != B3Status.SUCCESS) {
				if (status == B3Status.RUNNING) {
					tick.getBlackboard().setParam(Const.RUNNING_CHILD, i, tick.getTree().getId(), this.getId());
				}
				return status;
			}
		}

		return B3Status.SUCCESS;
	}

}
