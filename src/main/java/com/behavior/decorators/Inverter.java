package com.behavior.decorators;


import com.behavior.annotation.BehaviorNode;
import com.behavior.constant.B3Status;
import com.behavior.core.Decorator;
import com.behavior.core.Tick;
import com.behavior.enums.NodeTypeEnums;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
@BehaviorNode(TYPE_ENUMS = NodeTypeEnums.DECORATOR)
public class Inverter extends Decorator {

	@Override
	public B3Status onTick(Tick tick) {
		if (this.getChild() == null) {
			return B3Status.ERROR;
		}
		B3Status status = this.getChild().execute(tick);
		if (status == B3Status.SUCCESS) {
			status = B3Status.FAILURE;
		} else if (status == B3Status.FAILURE) {
			status = B3Status.SUCCESS;
		}
		return status;
	}

}
