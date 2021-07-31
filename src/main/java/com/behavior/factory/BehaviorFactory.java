package com.behavior.factory;


import com.behavior.core.IAction;

import java.util.ServiceLoader;


/**
 * 行为树工厂
 */
public class BehaviorFactory {

	public static void main(String[] args) {
		ServiceLoader<IAction> load = ServiceLoader.load(IAction.class);
		for (IAction iAction : load) {
			System.out.println(iAction.getClass().getName());
		}
	}

}
