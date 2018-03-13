package com.ipooleth.platform.secure;

import com.ipooleth.common.utils.JasonUtil;
import com.ipooleth.common.utils.common.ErrorException;
import com.ipooleth.common.utils.common.InvocationResult;
import com.ipooleth.platform.util.AppProperties;
import com.ipooleth.platform.util.Constansts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class WebSecurityInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String UNKNOWN = "unknown";


    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";

    private static final String X_FORWARDED_FOR = "x-forwarded-for";

    private static final String LOCAL_HOST = "localhost";

    private static final String LOOP_BACK_IP = "127.0.0.1";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        if(request.getRequestURL().indexOf("swagger")!=-1 || request.getRequestURL().indexOf("api-docs")!=-1)
        {
            if(StringUtils.isEmpty(AppProperties.evn) && AppProperties.evn.equals("PD")) {
                return false;
            }
            return true;
        }



        boolean checkIpWhiteList = isLogin(request);
        if (!checkIpWhiteList) {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            ErrorException ee = new ErrorException("error.authentication");
            InvocationResult ir = new InvocationResult(false, ee.getErrcode(), ee.getErrm(), "");
            response.getWriter().write(JasonUtil.toJson(ir));
        }
        return checkIpWhiteList;
    }

    private boolean isLogin(HttpServletRequest request) throws Exception {
        logger.info(" request.getRequestedSessionId():"+ request.getRequestedSessionId());
        logger.info(" request.getSession().getId():"+ request.getSession().getId());
        Object user = request.getSession().getAttribute(Constansts.USER_KEY);
        return !StringUtils.isEmpty(user);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("afterCompletion");

    }

}
