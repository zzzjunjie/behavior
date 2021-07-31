package com.behavior.core;


import com.behavior.config.BTNodeCfg;
import com.behavior.constant.B3Status;


/**
 * 节点信息
 */
public interface INode extends IBaseWrapper {

	/** 初始化节点 */
	void initialize(BTNodeCfg nodeCfg);

	/** 获取节点类型 */
	String getCategory();

	/** 执行节点 */
	B3Status execute(Tick tick);

	/** 获取节点名称 */
	String getName();

	/** 获取节点变标题 */
	String getTitle();

}
