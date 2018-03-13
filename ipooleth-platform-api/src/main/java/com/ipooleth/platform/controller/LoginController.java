package com.ipooleth.platform.controller;

import com.ipooleth.common.utils.common.ErrorException;
import com.ipooleth.common.utils.common.InvocationResult;
import com.ipooleth.common.utils.common.InvokationParameter;
import com.ipooleth.jpa.repository.MinePoolRepository;
import com.ipooleth.platform.util.Constansts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Api(tags = "USER", description = "LOGIN")
@RestController
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class);


    @Autowired
    private MinePoolRepository minePoolRepository;


    @ApiOperation(value = "login", position = 1, notes = "{\"param\": \"{\\\"account\\\":\\\"liu123\\\",\\\"password\\\":\\\"liu123pwd\\\"}\",\"channel\":\"ETH\",\"client\":\"H5\"}")
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public InvocationResult login(@RequestBody InvokationParameter parameter, HttpServletRequest request) {
        logger.info("method stats start!," + parameter.toString());
        InvocationResult invocationResult = null;
        String response = "";
        try {
            //参数校验  略...
            request.getSession().setAttribute(Constansts.USER_KEY,parameter.getParam());
            invocationResult = new InvocationResult(true, "");
        } catch (Exception e) {
            if (e instanceof ErrorException) {
                invocationResult = new InvocationResult(false, ((ErrorException) e).getErrcode(), ((ErrorException) e).getErrm(), "");
            } else {
                ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
                invocationResult = new InvocationResult(false, ex.getErrcode(), ex.getErrm(), "");
                logger.error("method stats  exception!", e);
            }

        }
        logger.info("method stats end!");
        return invocationResult;
    }


    @ApiOperation(value = "login", position = 1, notes = "{\"param\": \"{\\\"account\\\":\\\"liu123\\\",\\\"password\\\":\\\"liu123pwd\\\"}\",\"channel\":\"ETH\",\"client\":\"H5\"}")
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public InvocationResult login( HttpServletRequest request) {
        logger.info("method stats start!,");
        InvocationResult invocationResult = null;
        String response = "";
        try {
            String param = request.getParameter("param");
            //参数校验  略...
            request.getSession().setAttribute(Constansts.USER_KEY,param);
            invocationResult = new InvocationResult(true, param);
        } catch (Exception e) {
            if (e instanceof ErrorException) {
                invocationResult = new InvocationResult(false, ((ErrorException) e).getErrcode(), ((ErrorException) e).getErrm(), "");
            } else {
                ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
                invocationResult = new InvocationResult(false, ex.getErrcode(), ex.getErrm(), "");
                logger.error("method stats  exception!", e);
            }

        }
        logger.info("method stats end!");
        return invocationResult;
    }



}
