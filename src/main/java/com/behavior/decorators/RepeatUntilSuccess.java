package com.behavior.decorators;


import com.behavior.annotation.BehaviorNode;
import com.behavior.config.BTNodeCfg;
import com.behavior.constant.Const;
import com.behavior.constant.B3Status;
import com.behavior.core.Decorator;
import com.behavior.core.Tick;
import com.behavior.enums.NodeTypeEnums;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
@BehaviorNode(TYPE_ENUMS = NodeTypeEnums.DECORATOR)
public class RepeatUntilSuccess extends Decorator {

	private int maxLoop;

	@Override
	public void initialize(BTNodeCfg nodeCfg) {
		super.initialize(nodeCfg);
		this.maxLoop = Integer.valueOf(nodeCfg.getProperties().get(Const.MAX_LOOP));
	}

	@Override
	public void onOpen(Tick tick) {
		super.onOpen(tick);
		tick.getBlackboard().setParam("i", 0, tick.getTree().getId(), this.getId());
	}

	@Override
	public B3Status onTick(Tick tick) {

		if (this.getChild() == null) {
			return B3Status.FAILURE;
		}

		Integer i = tick.getBlackboard().getParam("i", tick.getTree().getId(), this.getId());
		B3Status status = B3Status.ERROR;

		while (i < maxLoop) {
			status = this.getChild().execute(tick);
			if (status == B3Status.FAILURE) {
				i++;
			} else {
				break;
			}
		}

		tick.getBlackboard().setParam("i", i, tick.getTree().getId(), this.getId());
		return status;
	}

}
