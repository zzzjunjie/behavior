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
public class Limiter extends Decorator {

	private int maxLoop;

	@Override
	public void initialize(BTNodeCfg nodeCfg) {
		super.initialize(nodeCfg);

		String ml = nodeCfg.getProperties().get(Const.MAX_LOOP);
		maxLoop = Integer.valueOf(ml);
	}

	@Override
	public void onOpen(Tick tick) {
		super.onOpen(tick);
		tick.getBlackboard().setParam("i", 0, tick.getTree().getId(), this.getId());
	}

	@Override
	public B3Status onTick(Tick tick) {

		Integer i = tick.getBlackboard().getParam("i", tick.getTree().getId(), this.getId());
		if (i < this.maxLoop) {

			B3Status status = this.getChild().execute(tick);

			if (status == B3Status.SUCCESS || B3Status.FAILURE == status) {
				tick.getBlackboard().setParam("i", i + 1, tick.getTree().getId(), this.getId());
			}
			return status;
		}

		return B3Status.FAILURE;
	}

}
