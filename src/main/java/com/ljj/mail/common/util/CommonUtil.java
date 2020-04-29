package com.ljj.mail.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * CommonUtil
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:36
 */
public class CommonUtil {
    private static ObjectMapper mapper;

    public static synchronized ObjectMapper getMapperInstance(boolean createNew) {
        if (createNew) {
            return new ObjectMapper();
        } else if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }
}
