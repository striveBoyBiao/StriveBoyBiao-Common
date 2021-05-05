package com.zizhuling.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zizhuling.common.dto.WrapperResponse;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>
 *     测试
 * </P>
 *
 * @author hebiao Create on 2020/7/23 16:19
 * @version 1.0
 */
public class UtilTest {

    @Test
    public void demo(){
        WrapperResponse wrapperResponse = WrapperResponse.success("success");
        System.out.println(JSON.toJSON(wrapperResponse));
        System.out.println(wrapperResponse);
    }


}
