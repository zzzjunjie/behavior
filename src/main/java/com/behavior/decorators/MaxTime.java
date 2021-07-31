package com.behavior.decorators;


import com.behavior.constant.B3Const;
import com.behavior.constant.B3Status;
import com.behavior.core.Decorator;
import com.behavior.core.Tick;
import com.behavior.config.BTNodeCfg;


/**
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
public class MaxTime extends Decorator {

	private long maxTime;

	@Override
	public void initialize(BTNodeCfg nodeCfg) {
		super.initialize(nodeCfg);

		maxTime = Long.valueOf(nodeCfg.getProperties().get(B3Const.MAX_TIME));
	}

	@Override
	public void onOpen(Tick tick) {
		super.onOpen(tick);

		long startTime = System.currentTimeMillis();

		tick.getBlackboard().setParam(B3Const.START_TIME, startTime, tick.getTree().getId(), this.getId());
	}

	@Override
	public B3Status onTick(Tick tick) {

		if (this.getChild() == null) {
			return B3Status.ERROR;
		}

		long currTime = System.currentTimeMillis();
		Long startTime = tick.getBlackboard().getParam(B3Const.START_TIME, tick.getTree().getId(), this.getId());
		B3Status status = this.getChild().execute(tick);
		if (currTime - startTime > this.maxTime) {
			return B3Status.FAILURE;
		}

		return status;
	}

}
