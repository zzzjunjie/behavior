package com.behavior.core;


import com.behavior.config.BTNodeCfg;
import com.behavior.config.BTTreeCfg;
import com.behavior.constant.B3Status;
import com.behavior.constant.Const;
import com.behavior.util.SpringContextUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 行为树
 */
@Getter
@Setter
@NoArgsConstructor
@Log4j2
public class BehaviorTree {

	// 行为树唯一ID
	private String id;

	// 行为树标题
	private String titile;

	// 行为树描述
	private String description;

	// 行为树属性 K:属性名 V:属性值
	private Map<String, Object> properties = new HashMap<>();

	// 行为树ROOT节点
	private BaseNode root;

	// 行为树项目
	private BehaviorTreeProject projectInfo;

	public BehaviorTree(String id) {
		this.id = id;
	}

	/**
	 * 载入行为树
	 *
	 * @param cfg         行为树配置
	 */
	public void load(BTTreeCfg cfg) {
		// 初始化配置信息
		this.titile = cfg.getTitle();
		this.description = cfg.getDescription();
		this.properties = cfg.getProperties();
		// 行为树中所有的节点
		Map<String, BaseNode> nodes = new HashMap<>();
		// 循环创建行为树中的配置节点
		for (Map.Entry<String, BTNodeCfg> nodeEntry : cfg.getNodes().entrySet()) {
			// 节点ID
			String id = nodeEntry.getKey();
			// 节点配置信息
			BTNodeCfg nodeCfg = nodeEntry.getValue();
			// 每一个节点
			BaseNode node = null;
			// 检查节点类型是否为子数类型
			if (Const.SUBTREE.equals(nodeCfg.getCategory())) {
				// TODO 如果是子树类型的话如何处理呢？
				// node = SpringContextUtils.getBean()
			} else {
				// 普通结点加载,根据名字来加载
				Object bean = SpringContextUtils.getBean(nodeCfg.getName());
				node = (BaseNode) bean;
			}
			// 节点信息初始化
			node.initialize(nodeCfg);

			if (projectInfo != null) {
				node.setProjectInfo(projectInfo);
			}
			nodes.put(id, node);
		}

		// 连接行为树
		for (Map.Entry<String, BTNodeCfg> nodeEntry : cfg.getNodes().entrySet()) {
			// 根据节点名称获取节点
			BaseNode node = nodes.get(nodeEntry.getKey());
			// 节点的配置信息
			BTNodeCfg nodeCfg = nodeEntry.getValue();
			// 如果是组合节点的话，循环遍历子节点
			if (Const.COMPOSITE.equals(node.getCategory()) && !CollectionUtils.isEmpty(nodeCfg.getChildren())) {
				for (String cid : nodeCfg.getChildren()) {
					IComposite comp = (IComposite) node;
					comp.addChild(nodes.get(cid));
				}
			} else if (Const.DECORATOR.equals(node.getCategory()) && !CollectionUtils.isEmpty(nodeCfg.getChildren())) {
				IDecorator deco = (IDecorator) node;
				deco.setChild(nodes.get(nodeCfg.getChild()));
			}
		}

		//设置root节点
		this.root = nodes.get(cfg.getRoot());
	}

	/**
	 * 开始执行行为树
	 *
	 * @param t          拥有这个tick的目标
	 * @param blackboard 黑板
	 * @param <T>        目标类型
	 * @return 行为树运作状态
	 */
	public <T> B3Status tick(T t, Blackboard blackboard) {

		if (blackboard == null) {
			log.error("The blackboard parameter is obligatory and must be an instance of b3.Blackboard");
			return B3Status.ERROR;
		}

		/* CREATE A TICK OBJECT */
		Tick tick = new Tick();
		tick.setTarget(t);
		tick.setBlackboard(blackboard);
		tick.setTree(this);
		// 根节点开始运行
		B3Status status = this.root.run(tick);
		// 打开节点
		List<BaseNode> lastOpenNodes = blackboard.getTreeData(this.id).openNodes;

		List<BaseNode> currOpenNodes = tick.getOpenNodes();

		// does not close if it is still open in this tick
		int start = 0;
		for (int i = 0; i < (Math.min(lastOpenNodes.size(), currOpenNodes.size())); i++) {
			start = i + 1;
			if (lastOpenNodes.get(i) != currOpenNodes.get(i)) {
				break;
			}
		}

		// close the nodes
		for (int i = lastOpenNodes.size() - 1; i >= start; i--) {
			lastOpenNodes.get(i).close(tick);
		}

		/* POPULATE BLACKBOARD */
		blackboard.getTreeData(this.id).openNodes = currOpenNodes;
		blackboard.SetTree("nodeCount", tick.getNodeCount(), this.id);

		return status;
	}

}
