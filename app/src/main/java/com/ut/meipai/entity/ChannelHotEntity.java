package com.ut.meipai.entity;


/**
 * Created by LZM on 2017/4/19.
 * Function:
 * Desc:
 */

public class ChannelHotEntity {

    public ChannelHotEntity(String logo, String name, double playNumber) {
        this.logo = logo;
        this.name = name;
        this.playNumber = playNumber;
    }

    public String logo;
    public String name;
    public double playNumber;
    public boolean isHot;
    public boolean isAward;
}
