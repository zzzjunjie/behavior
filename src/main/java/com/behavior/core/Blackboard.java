package com.behavior.core;


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

	// 黑板中记录的行为树，一个黑板可以记录多个行为树运行的记录
	private Map<String, TreeMemory> treeMemory = new HashMap<>();

	/**
	 * 根据树的ID获取对应树的运行数据
	 *
	 * @param treeScope 树ID
	 * @return 树数据
	 */
	TreeData getTreeData(String treeScope) {

		if (treeMemory.get(treeScope) == null) {
			return new TreeData();
		}

		return treeMemory.get(treeScope).getTreeData();
	}

	/**
	 * 设置树自身的参数
	 *
	 * @param key       参数名
	 * @param object    存储对象
	 * @param treeScope 树ID
	 */
	void SetTree(String key, Object object, String treeScope) {
		// 默认将空的节点Id给树用
		Memory memory = this.getMemory(treeScope, "");

		memory.getMemeory().put(key, object);
	}

	/**
	 * 获取树的运行内存数据
	 *
	 * @param treeScope 树的ID
	 * @return 树的内存数据
	 */
	private TreeMemory getTreeMemory(String treeScope) {

		TreeMemory tm = treeMemory.get(treeScope);
		if (tm == null) {
			tm = new TreeMemory();
			treeMemory.put(treeScope, tm);
		}

		return tm;
	}

	/**
	 * 获取树种某一个节点的内存信息
	 *
	 * @param treeScope 树ID
	 * @param nodeScope 节点ID
	 * @return 返回树种某个节点运行的内存信息
	 */
	public Memory getMemory(String treeScope, String nodeScope) {

		TreeMemory tm = getTreeMemory(treeScope);

		return getNodeMemory(tm, nodeScope);
	}

	/**
	 * 获取boolean参数
	 *
	 * @param key       参数Key
	 * @param treeScope 树Id
	 * @param nodeScope 节点ID
	 * @return 节点运行产生的boolean值
	 */
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

	/**
	 * 获取节点内存信息
	 *
	 * @param treeMemory 树内存
	 * @param nodeScope  节点Id
	 * @return 返回树节点中某一个节点运行的数据信息
	 */
	private Memory getNodeMemory(TreeMemory treeMemory, String nodeScope) {

		Memory memory = treeMemory.getNodeMemory().get(nodeScope);

		if (memory == null) {

			memory = new Memory();
			treeMemory.getNodeMemory().put(nodeScope, memory);
		}
		return memory;
	}

	/**
	 * 添加参数
	 * @param key 参数Key
	 * @param value 参数值
	 * @param treeScope 树ID
	 * @param nodeScope 节点ID
	 */
	public void setParam(String key, Object value, String treeScope, String nodeScope) {
		Memory memory = getMemory(treeScope, nodeScope);
		memory.getMemeory().put(key, value);
	}

	/**
	 * 获取参数
	 * @param key 参数Key
	 * @param treeScope 树ID
	 * @param nodeScope 节点ID
	 * @param <T> 参数类型
	 * @return 参数值
	 */
	@SuppressWarnings("unchecked")
	public <T> T getParam(String key, String treeScope, String nodeScope) {
		Memory memory = getMemory(treeScope, nodeScope);
		Object object = memory.getMemeory().get(key);
		return (T) object;
	}

	/**
	 * 树数据，这里存储一棵树的所有数据信息
	 */
	@Data
	class TreeData {

		// 树运行过程中产生的临时存储参数
		Memory nodeMemory = new Memory();

		// 打开的节点列表
		List<BaseNode> openNodes = new ArrayList<>();

		// 树的深度
		private int traversalDepth;

		// 广度
		private int traversalCycle;

	}


	/**
	 * 存储数据
	 */
	@Data
	public class Memory {

		// K:参数名 V:存储的值
		private Map<String, Object> memeory = new HashMap<>();

		public Object get(String key) {
			return memeory.get(key);
		}

	}


	/**
	 * 树信息
	 */
	@Data
	public class TreeMemory {

		// 树数据信息
		private TreeData treeData = new TreeData();

		// 树里每一个节点里面存储的信息 K:节点ID V:节点运行产生的信息
		private Map<String, Memory> nodeMemory = new HashMap<>();

	}

}
