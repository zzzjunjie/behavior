package com.behavior.annotation;


import com.behavior.enums.NodeTypeEnums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BehaviorNode {

	/** 节点类型 */
	NodeTypeEnums TYPE_ENUMS();

}
