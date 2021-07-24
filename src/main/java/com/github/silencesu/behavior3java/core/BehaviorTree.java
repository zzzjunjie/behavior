package com.github.silencesu.behavior3java.core;


import com.github.silencesu.behavior3java.config.BTNodeCfg;
import com.github.silencesu.behavior3java.config.BTTreeCfg;
import com.github.silencesu.behavior3java.config.DefaultNodes;
import com.github.silencesu.behavior3java.constant.B3Const;
import com.github.silencesu.behavior3java.constant.B3Status;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 行为树
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
@Getter
@Setter
@Slf4j
public class BehaviorTree {

	// 行为树唯一ID
	private String id = UUID.randomUUID().toString().replaceAll("-", "");

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

	/**
	 * 载入行为树
	 *
	 * @param cfg 行为树配置
	 */
	public void load(BTTreeCfg cfg) {
		load(cfg, new HashMap<>());
	}

	/**
	 * 载入行为树
	 *
	 * @param cfg         行为树配置
	 * @param extendNodes 扩展节点
	 */
	public void load(BTTreeCfg cfg, Map<String, Class<? extends BaseNode>> extendNodes) {
		// 初始化配置信息
		this.titile = cfg.getTitle();
		this.description = cfg.getDescription();
		this.properties = cfg.getProperties();
		// 获取项目中的默认节点
		Map<String, Class<? extends BaseNode>> nodeMaps = new HashMap<>(DefaultNodes.get());
		// 加载扩展nodes
		if (extendNodes != null && extendNodes.size() > 0) {
			nodeMaps.putAll(extendNodes);
		}
		// 行为树中所有的节点
		Map<String, BaseNode> nodes = new HashMap<>();
		// 循环创建行为树中的配置节点
		for (Map.Entry<String, BTNodeCfg> nodeEntry : cfg.getNodes().entrySet()) {
			// 节点ID
			String id = nodeEntry.getKey();
			// 节点配置信息
			BTNodeCfg nodeCfg = nodeEntry.getValue();

			BaseNode node = null;

			// 检查节点类型是否为子数类型
			if (B3Const.SUBTREE.equals(nodeCfg.getCategory())) {
				node = new SubTree();
			} else {
				// 普通结点加载,根据名字来加载
				Class<? extends BaseNode> clazz = nodeMaps.get(nodeCfg.getName());
				if (clazz != null) {
					try {
						// 实例化节点
						node = clazz.getDeclaredConstructor().newInstance();
					} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			// 节点实例化失败
			if (node == null) {
				log.error("create node erro:{}", nodeCfg.getName());
				break;
			}
			// 节点信息初始化
			node.initialize(nodeCfg);

			if (projectInfo != null) {
				node.setProjectInfo(projectInfo);
			}

			nodes.put(id, node);
		}

		/**
		 * 连接行为树
		 */
		for (Map.Entry<String, BTNodeCfg> nodeEntry : cfg.getNodes().entrySet()) {
			// 根据节点名称获取节点
			BaseNode node = nodes.get(nodeEntry.getKey());
			// 节点的配置信息
			BTNodeCfg nodeCfg = nodeEntry.getValue();
			// 如果是组合节点的话，循环遍历子节点
			if (B3Const.COMPOSITE.equals(node.getCategory()) && !CollectionUtils.isEmpty(nodeCfg.getChildren())) {
				for (String cid : nodeCfg.getChildren()) {
					IComposite comp = (IComposite) node;
					comp.addChild(nodes.get(cid));
				}
			} else if (B3Const.DECORATOR.equals(node.getCategory()) && !CollectionUtils.isEmpty(nodeCfg.getChildren())) {
				IDecorator deco = (IDecorator) node;
				deco.setChild(nodes.get(nodeCfg.getChild()));
			}
		}

		//设置root节点
		this.root = nodes.get(cfg.getRoot());
	}

	/**
	 * 开始执行行为树
	 * @param t 拥有这个tick的目标
	 * @param blackboard 黑板
	 * @param <T> 目标类型
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
