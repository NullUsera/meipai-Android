package com.ut.meipai.data;

import com.ut.meipai.entity.MyFocusEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 任和 on 2017/04/17 16:45
 * Function:
 * Desc:
 */
public class MyFocusData {

    /**
     * 获取我关注的视频
     */
    public static List<MyFocusEntity> getSampleData() {
        String avatar = "http://himg2.huanqiu.com/attachment2010/2012/1017/20121017103004763.jpg";
        List<MyFocusEntity> focusEntities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // String userName, String userAvatar, String createDate, String videoId,
            // String videoLink, int likeCount
            MyFocusEntity focusEntity = new MyFocusEntity("user-" + i, avatar, "04-17 17:30",
                    UUID.randomUUID().toString(), "", i * 10);
            focusEntities.add(focusEntity);
        }
        return focusEntities;
    }
}
