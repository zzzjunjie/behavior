package com.behavior.config;


import com.behavior.constant.Const;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = Const.Config.BEHAVIOR)
public class BehaviorConfig {

	/** 行为树路径存放文件夹"*/
	private String path;
}
