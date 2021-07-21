package com.github.silencesu.behavior3java.config;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Setter
@Getter
public class BTNodeCfg {

	// 节点ID
	private String id;

	// 节点名称
	private String name;

	// 节点标题
	private String title;

	// 节点类型
	private String category;

	// 节点描述
	private String description;

	// 子节点列表
	private List<String> children;

	// 子节点
	private String child;

	// 参数
	private Map<String, String> parameters;

	// 属性
	private Map<String, String> properties;

}
