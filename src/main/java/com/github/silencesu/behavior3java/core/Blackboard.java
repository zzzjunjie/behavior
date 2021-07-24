package com.github.silencesu.behavior3java.core;


import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 黑板报
 * k-v存储数据
 */
@Data
public class Blackboard {

	//    private Memory baseMemory;
	private Map<String, TreeMemory> treeMemory = new HashMap<>();

	TreeData getTreeData(String treeScope) {

		if (treeMemory.get(treeScope) == null) {
			return new TreeData();
		}

		return treeMemory.get(treeScope).getTreeData();
	}

	void SetTree(String key, Object object, String treeScope) {

		Memory memory = this.getMemory(treeScope, "");

		memory.getMemeory().put(key, object);
	}

	private TreeMemory getTreeMemory(String treeScope) {

		TreeMemory tm = treeMemory.get(treeScope);
		if (tm == null) {
			tm = new TreeMemory();
			treeMemory.put(treeScope, tm);
		}

		return tm;
	}

	public Memory getMemory(String treeScope, String nodeScope) {

		TreeMemory tm = getTreeMemory(treeScope);

		return getNodeMemory(tm, nodeScope);
	}

	Boolean getBool(String key, String treeScope, String nodeScope) {

		Memory memory = getMemory(treeScope, nodeScope);
		if (memory == null) {
			return false;
		}
		Object object = memory.getMemeory().get(key);
		if (object == null) {
			return false;
		}
		return (Boolean) object;
	}

	private Memory getNodeMemory(TreeMemory treeMemory, String nodeScope) {

		Memory memory = treeMemory.getNodeMemory().get(nodeScope);

		if (memory == null) {

			memory = new Memory();
			treeMemory.getNodeMemory().put(nodeScope, memory);
		}
		return memory;
	}

	public void setParam(String key, Object value, String treeScope, String nodeScope) {
		Memory memory = getMemory(treeScope, nodeScope);
		memory.getMemeory().put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getParam(String key, String treeScope, String nodeScope) {
		Memory memory = getMemory(treeScope, nodeScope);
		Object object = memory.getMemeory().get(key);
		return (T) object;
	}

	@Data
	class TreeData {

		Memory nodeMemory = new Memory();

		List<BaseNode> openNodes = new ArrayList<>();

		private int traversalDepth;

		private int traversalCycle;

	}


	@Data
	public class Memory {

		private Map<String, Object> memeory = new HashMap<>();

		public Object get(String key) {
			return memeory.get(key);
		}

	}


	@Data
	public class TreeMemory {

		private TreeData treeData = new TreeData();

		private Map<String, Memory> nodeMemory = new HashMap<>();

	}

}
