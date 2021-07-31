package com.behavior.core;


import com.behavior.annotation.BehaviorNode;
import com.behavior.constant.B3Status;
import com.behavior.constant.Const;
import com.behavior.enums.NodeTypeEnums;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * 行为节点 子树节点
 */
public class SubTree extends Action {

	private BehaviorTree subTree;

	@Override
	public String getCategory() {
		return Const.ACTION;
	}

	@Override
	public B3Status onTick(Tick tick) {
		//子树可能没有加载上来，所以要延迟加载执行
		if (subTree == null) {
			//此处name为子树的id
			subTree = projectInfo.findBTTreeById(getName());
		}
		if (subTree == null) {
			return B3Status.ERROR;
		}
		return subTree.tick(tick.getTarget(), tick.getBlackboard());
	}

}
