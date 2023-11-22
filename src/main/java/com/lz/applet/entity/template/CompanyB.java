package com.lz.applet.entity.template;

public class CompanyB extends AskForLeaveFlow {
    @Override
    protected void firstGroupLeader(String name) {
        System.out.println("CompanyB 组内有人请假，请假人:"+name);
    }

    @Override
    protected void secondGroupLeader(String name) {
        System.out.println("CompanyB 部门有人请假："+name);
    }
}
