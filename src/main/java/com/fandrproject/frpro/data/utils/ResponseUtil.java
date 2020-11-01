package com.fandrproject.frpro.data.utils;

import com.fandrproject.frpro.data.common.UnifyResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);


    public static void out(HttpServletResponse response, UnifyResult r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            if(response.getWriter() == null) {
                return;
            }
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            LOGGER.error(String.format("返回数据异常-异常原因是:%s",
                    e.getMessage()), e);
        }
    }

}
