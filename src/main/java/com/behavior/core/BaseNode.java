package com.behavior.core;


import com.behavior.config.BTNodeCfg;
import com.behavior.constant.B3Status;
import lombok.Data;

import java.util.Map;


/**
 * 基础节点
 */
@Data
public abstract class BaseNode implements INode, INodeWorker {

	// 节点ID
	private String id;

	// 节点给名称
	private String name;

	// 节点标题
	private String title;

	// 节点描述
	private String description;

	// 节点参数
	private Map<String, String> parameters;

	// 节点属性
	private Map<String, String> properties;

	// 节点所属工程信息
	protected BehaviorTreeProject projectInfo;

	/**
	 * 开始
	 *
	 * @param tick
	 */
	@Override
	public void onEnter(Tick tick) {

	}

	/**
	 * 打开
	 *
	 * @param tick
	 */
	@Override
	public void onOpen(Tick tick) {

	}

	/**
	 * 关闭
	 *
	 * @param tick
	 */
	@Override
	public void onClose(Tick tick) {

	}

	/**
	 * 退出
	 *
	 * @param tick
	 */
	@Override
	public void onExit(Tick tick) {

	}

	/**
	 * 初始化
	 *
	 * @param nodeCfg 节点配置信息
	 */
	@Override
	public void initialize(BTNodeCfg nodeCfg) {

		this.id = nodeCfg.getId();
		this.name = nodeCfg.getName();
		this.title = nodeCfg.getTitle();
		this.description = nodeCfg.getDescription();
		this.parameters = nodeCfg.getParameters();
		this.properties = nodeCfg.getProperties();
	}

	@Override
	public B3Status run(Tick tick) {
		// 进入节点
		this.enter(tick);
		// 如果节点没有打开，就执行打开
		if (!tick.getBlackboard().getBool("isOpen", tick.getTree().getId(), this.id)) {
			this.open(tick);
		}
		// 节点运行
		B3Status status = this.tick(tick);
		// 节点
		if (status != B3Status.RUNNING) {
			this.onClose(tick);
		}

		this.exit(tick);

		return status;
	}

	@Override
	public void enter(Tick tick) {
		tick.enterNode(this);
		this.onEnter(tick);
	}

	@Override
	public void open(Tick tick) {

		tick.openNode(this);

		tick.getBlackboard().setParam("isOpen", true, tick.getTree().getId(), this.getId());

		this.onOpen(tick);
	}

	@Override
	public B3Status tick(Tick tick) {

		tick.tickNode(this);

		return this.onTick(tick);
	}

	@Override
	public void close(Tick tick) {
		tick.closeNode(this);
		tick.getBlackboard().setParam("isOpen", false, tick.getTree().getId(), this.getId());
		this.onClose(tick);
	}

	@Override
	public void exit(Tick tick) {
		tick.exitNNode(this);
		this.onExit(tick);
	}

	@Override
	public B3Status execute(Tick tick) {
		return this.run(tick);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

}
