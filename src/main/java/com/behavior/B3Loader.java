package com.behavior;


import com.behavior.config.BTTreeCfg;
import com.behavior.config.BTTreeProjectCfg;
import com.behavior.config.BevTreeConfig;
import com.behavior.core.BaseNode;
import com.behavior.core.BehaviorTree;
import com.behavior.core.BehaviorTreeProject;

import java.util.Map;


/**
 * 行为树加载器
 */
public class B3Loader {

	/**
	 * @param treeJson    行为树配置文件
	 */
	public static BehaviorTree loadB3Tree(String treeJson) {

		// 配置文件解析成对应实体
		BTTreeCfg btTreeCfg = BevTreeConfig.LoadTreeCfg(treeJson);

		// 配置文件转为树
		BehaviorTree tree = new BehaviorTree();
		tree.load(btTreeCfg);

		return tree;
	}

	/**
	 * 加载工程
	 *
	 * @param projectJson 项目JSON文件
	 * @param extendNodes 加载扩展的节点
	 * @return 行为树项目
	 */
	public static BehaviorTreeProject loadB3Project(String projectJson, Map<String, Class<? extends BaseNode>> extendNodes) {
		BTTreeProjectCfg projectCfg = BevTreeConfig.LoadBTTreePorjectCfg(projectJson);
		BehaviorTreeProject project = new BehaviorTreeProject();
		project.initProject(projectCfg, extendNodes);
		return project;
	}

}
