package com.behavior.config;


import lombok.Getter;
import lombok.Setter;


/**
 * 行为树工程配置
 */
@Setter
@Getter
public class BTTreeProjectCfg {

	private String name;

	private String description;

	private String scope;

	private BTTreeProjectDataCfg data;

}
