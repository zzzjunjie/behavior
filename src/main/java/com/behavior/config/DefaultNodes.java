package com.behavior.config;


import com.behavior.actions.*;
import com.behavior.actions.Error;
import com.behavior.composites.MemPriority;
import com.behavior.composites.MemSequence;
import com.behavior.composites.Priority;
import com.behavior.composites.Sequence;
import com.behavior.core.BaseNode;
import com.behavior.decorators.*;
import com.behavior.core.SubTree;

import java.util.HashMap;
import java.util.Map;


/**
 * 默认节点
 */
public class DefaultNodes {

	/**
	 * 注册支持的节点
	 * key name
	 * value node class
	 */
	private static Map<String, Class<? extends BaseNode>> defaultNodes = new HashMap<>();

	static {
		//actions 行为节点
		defaultNodes.put("Error", Error.class);
		defaultNodes.put("Failer", Failer.class);
		defaultNodes.put("Runner", Runner.class);
		defaultNodes.put("Succeeder", Succeeder.class);
		defaultNodes.put("Wait", Wait.class);
		defaultNodes.put("SubTree", SubTree.class);

		//composites 复合节点
		defaultNodes.put("MemPriority", MemPriority.class);
		defaultNodes.put("MemSequence", MemSequence.class);
		defaultNodes.put("Priority", Priority.class);
		defaultNodes.put("Sequence", Sequence.class);

		//decorators 修饰节点
		defaultNodes.put("Inverter", Inverter.class);
		defaultNodes.put("Limiter", Limiter.class);
		defaultNodes.put("MaxTime", MaxTime.class);
		defaultNodes.put("Repeater", Repeater.class);
		defaultNodes.put("RepeatUntilFailure", RepeatUntilFailure.class);
		defaultNodes.put("RepeatUntilSuccess", RepeatUntilSuccess.class);
	}

	/**
	 * 扩充Nodes节点
	 */
	public static Map<String, Class<? extends BaseNode>> extendCustomNodes(Map<String, Class<? extends BaseNode>> maps) {
		defaultNodes.putAll(maps);
		return defaultNodes;
	}

	public static Map<String, Class<? extends BaseNode>> get() {
		return defaultNodes;
	}

}
