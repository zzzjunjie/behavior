package com.github.silencesu.behavior3java.core;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * Tick 执行类
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
@Data
public class Tick {

	// 行为树
	private BehaviorTree tree;

	// 黑板
	private Blackboard blackboard;

	// 加入的节点
	private List<BaseNode> openNodes = new ArrayList<>();

	// 目标
	Object target;

	// 节点数量
	private int nodeCount;

	public Tick() {
		initialize();
	}

	public void initialize() {

		this.tree = null;
		this.blackboard = null;

		this.openNodes = new ArrayList<>();
		this.nodeCount = 0;
	}

	public String treeId() {
		return this.getTree().getId();
	}

	public BehaviorTree getTree() {
		return this.tree;
	}

	public Blackboard getBlackboard() {
		return this.blackboard;
	}

	public void enterNode(BaseNode node) {
		this.nodeCount++;
		this.openNodes.add(node);
	}

	public void openNode(BaseNode node) {

	}

	public void tickNode(BaseNode node) {

	}

	public void closeNode(BaseNode node) {

		if (this.openNodes.size() > 0) {
			this.openNodes.remove(node);
		}
	}

	public void exitNNode(BaseNode node) {

	}

}
