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
public class MaxTime extends Decorator {

	private long maxTime;

	@Override
	public void initialize(BTNodeCfg nodeCfg) {
		super.initialize(nodeCfg);

		maxTime = Long.valueOf(nodeCfg.getProperties().get(Const.MAX_TIME));
	}

	@Override
	public void onOpen(Tick tick) {
		super.onOpen(tick);

		long startTime = System.currentTimeMillis();

		tick.getBlackboard().setParam(Const.START_TIME, startTime, tick.getTree().getId(), this.getId());
	}

	@Override
	public B3Status onTick(Tick tick) {

		if (this.getChild() == null) {
			return B3Status.ERROR;
		}

		long currTime = System.currentTimeMillis();
		Long startTime = tick.getBlackboard().getParam(Const.START_TIME, tick.getTree().getId(), this.getId());
		B3Status status = this.getChild().execute(tick);
		if (currTime - startTime > this.maxTime) {
			return B3Status.FAILURE;
		}

		return status;
	}

}
