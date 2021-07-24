package com.github.silencesu.behavior3java.core;


import com.github.silencesu.behavior3java.config.BTTreeCfg;
import com.github.silencesu.behavior3java.config.BTTreeProjectCfg;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


/**
 * 行为树工程
 */
@Data
@Slf4j
public class BehaviorTreeProject {

	// K:行为树Title V:行为树
	private Map<String, BehaviorTree> titleTreeMap = new HashMap<>();
	// K:行为树ID V:行为树
	private Map<String, BehaviorTree> idTreeMap = new HashMap<>();

	/**
	 * 根据行为树标题查找对应的行为树
	 * @param treeTitle 行为树Title
	 * @return 行为树
	 */
	public BehaviorTree findBTTreeByTitle(String treeTitle) {
		return titleTreeMap.get(treeTitle.trim());
	}

	/**
	 * 根据行为树ID查找行为树
	 * @param id 行为树ID
	 * @return 行为树
	 */
	public BehaviorTree findBTTreeById(String id) {
		return idTreeMap.get(id);
	}

	/**
	 * 初始化工程数据数据
	 *
	 * @param projectCfg  工程配置
	 * @param extendNodes 扩展结点
	 */
	public void initProject(BTTreeProjectCfg projectCfg, Map<String, Class<? extends BaseNode>> extendNodes) {

		for (BTTreeCfg treeCfg : projectCfg.getData().getTrees()) {

			BehaviorTree behaviorTree = new BehaviorTree();
			behaviorTree.setProjectInfo(this);
			behaviorTree.load(treeCfg, extendNodes);

			titleTreeMap.put(treeCfg.getTitle(), behaviorTree);
			idTreeMap.put(treeCfg.getId(), behaviorTree);
		}
	}

}
