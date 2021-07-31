package com.behavior.actions;


import com.behavior.annotation.BehaviorNode;
import com.behavior.constant.B3Status;
import com.behavior.core.Action;
import com.behavior.core.Tick;
import com.behavior.enums.NodeTypeEnums;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * 允许行为节点
 */
@Component
@Scope("prototype")
@BehaviorNode(TYPE_ENUMS = NodeTypeEnums.ACTION)
public class Runner extends Action {

	@Override
	public B3Status onTick(Tick tick) {
		return B3Status.SUCCESS;
	}

}
