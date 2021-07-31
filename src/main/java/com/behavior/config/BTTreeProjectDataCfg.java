package com.behavior.config;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * 行为树工程数据配置
 */
@Setter
@Getter
public class BTTreeProjectDataCfg {

	private String version;

	private String scope;

	private String selectedTree;

	private List<BTTreeCfg> trees = new ArrayList<>();

}
