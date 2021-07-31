package com.behavior.condition;


import com.behavior.config.BTNodeCfg;
import com.behavior.constant.B3Status;
import com.behavior.core.Condition;
import com.behavior.core.Tick;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DemoCondition extends Condition {

	private String info;

	@Override
	public void initialize(BTNodeCfg nodeCfg) {
		super.initialize(nodeCfg);
		info = nodeCfg.getProperties().get("info");
	}

	@Override
	public B3Status onTick(Tick tick) {
		log.info("条件节点被执行了，参数为:{}", this.info);
		return B3Status.SUCCESS;
	}

}
