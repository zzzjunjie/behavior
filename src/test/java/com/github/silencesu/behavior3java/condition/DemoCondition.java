package com.github.silencesu.behavior3java.condition;


import com.github.silencesu.behavior3java.config.BTNodeCfg;
import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Condition;
import com.github.silencesu.behavior3java.core.Tick;
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
