package com.behavior.config;


import lombok.Data;

import java.util.Map;


/**
 * 行为树配置
 */
@Data
public class BTTreeCfg {

	// 树ID
	private String id;

	// 树标题
	private String title;

	// 树描述
	private String description;

	// 根节点
	private String root;

	// 属性
	private Map<String, Object> properties;

	// 节点
	private Map<String, BTNodeCfg> nodes;

}
