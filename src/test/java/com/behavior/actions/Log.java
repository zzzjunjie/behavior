package com.behavior.actions;


import com.behavior.config.BTNodeCfg;
import com.behavior.constant.B3Status;
import com.behavior.core.Action;
import com.behavior.core.Tick;


/**
 * 日志记录行为节点
 */
public class Log extends Action {
    private String info;


    @Override
    public void initialize(BTNodeCfg nodeCfg) {
        super.initialize(nodeCfg);
        info = nodeCfg.getProperties().get("info");
    }

    @Override
    public void onOpen(Tick tick) {
        super.onOpen(tick);
    }

    @Override
    public B3Status onTick(Tick tick) {
        System.out.println("action-log:"+ this.info);
        return B3Status.SUCCESS;
    }

}
