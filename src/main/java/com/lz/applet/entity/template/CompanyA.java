package com.lz.applet.entity.template;

public class CompanyA extends AskForLeaveFlow{

    @Override
    protected void firstGroupLeader(String name) {
        System.out.println("CompanyA 组内有人请假，请假人："+name);
    }
}
