package org.ame.yan.shabby.handler;

import com.alibaba.fastjson.JSON;
import org.ame.yan.shabby.constant.CommonConstant;
import org.ame.yan.shabby.model.vo.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理
 */
@Component
public class AuthenticationFailHandlerImpl implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType(CommonConstant.APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultVO.fail(e.getMessage())));
    }

}
