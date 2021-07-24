package com.github.silencesu.behavior3java;


import com.github.silencesu.behavior3java.config.BTTreeCfg;
import com.github.silencesu.behavior3java.config.BTTreeProjectCfg;
import com.github.silencesu.behavior3java.config.BevTreeConfig;
import com.github.silencesu.behavior3java.core.BaseNode;
import com.github.silencesu.behavior3java.core.BehaviorTree;
import com.github.silencesu.behavior3java.core.BehaviorTreeProject;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;


/**
 * 行为树加载器
 */
public class B3Loader {

	/**
	 * @param treeJson    行为树配置文件
	 * @param extendNodes 自定义扩展结点
	 */
	public static BehaviorTree loadB3Tree(String treeJson, Map<String, Class<? extends BaseNode>> extendNodes) {

		// 配置文件解析成对应实体
		BTTreeCfg btTreeCfg = BevTreeConfig.LoadTreeCfg(treeJson);

		// 配置文件转为树
		BehaviorTree tree = new BehaviorTree();
		if (!MapUtils.isEmpty(extendNodes)) {
			tree.load(btTreeCfg, extendNodes);
		} else {
			tree.load(btTreeCfg);
		}

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
