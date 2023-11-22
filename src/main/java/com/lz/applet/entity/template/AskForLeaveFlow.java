package com.lz.applet.entity.template;

/**
 * 抽象模板
 */
public abstract class AskForLeaveFlow {
    //一级组长审批
    protected abstract void firstGroupLeader(String name);
    //二级组长部门负责人审批
    protected void secondGroupLeader(String name){

    }

    //通知HR有人请假了
    private final void notifyHr(String name){
        System.out.println("当前有人请假了，请假人:"+name);
    }

    //请假模板
    public void askForLeave(String name){
        firstGroupLeader(name);
        secondGroupLeader(name);
        notifyHr(name);
    }
}
