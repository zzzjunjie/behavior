package com.behavior.actions;


import com.behavior.config.BTNodeCfg;
import com.behavior.constant.B3Const;
import com.behavior.constant.B3Status;
import com.behavior.core.Action;
import com.behavior.core.Tick;


/**
 * 等待行为节点
 */
public class Wait extends Action {

	private long endTime;

	@Override
	public void initialize(BTNodeCfg nodeCfg) {
		super.initialize(nodeCfg);
		String ml = nodeCfg.getProperties().get(B3Const.END_TIME);
		endTime = Long.parseLong(ml);
	}

	@Override
	public void onOpen(Tick tick) {
		super.onOpen(tick);

		long startTime = System.currentTimeMillis();
		tick.getBlackboard().setParam(B3Const.START_TIME, startTime, tick.getTree().getId(), this.getId());
	}

	@Override
	public B3Status onTick(Tick tick) {

		long currentTime = System.currentTimeMillis();
		Long startTime = tick.getBlackboard().getParam(B3Const.START_TIME, tick.getTree().getId(), this.getId());

		if (currentTime - startTime > this.endTime) {
			return B3Status.SUCCESS;
		}

		return B3Status.RUNNING;
	}

}
