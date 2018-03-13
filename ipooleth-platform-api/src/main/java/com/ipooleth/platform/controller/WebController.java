package com.ipooleth.platform.controller;


import com.ipooleth.common.utils.JasonUtil;
import com.ipooleth.common.utils.common.ErrorException;
import com.ipooleth.common.utils.common.InvocationResult;
import com.ipooleth.common.utils.common.InvokationParameter;
import com.ipooleth.jpa.domain.MinePool;
import com.ipooleth.jpa.domain.MinePoolApi;
import com.ipooleth.jpa.repository.MinePoolRepository;
import com.ipooleth.platform.util.Constansts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Api(tags ="TEST" , description = "TEST")
@RestController
@RequestMapping("/web")
public class WebController {

    private static final Logger logger = LogManager.getLogger(WebController.class);


    @Autowired
    private MinePoolRepository minePoolRepository;


    @ApiOperation(value = "ADD", position = 1, notes = "{\"param\": \"{\\\"id\\\":\\\"59afb9946ec03999f44393b1\\\",\\\"nameZh\\\":\\\"ETH-ZH\\\",\\\"nameCn\\\":\\\"ETH\\\",\\\"remark\\\":\\\"testtest\\\",\\\"minePoolApiList\\\":[{\\\"key\\\":\\\"STATS\\\",\\\"url\\\":\\\"http://www.ibichi.com:8080/api/stats\\\",\\\"remark\\\":\\\"aaaaa\\\"}],\\\"enable\\\":\\\"1\\\"}\",\"channel\":\"ETH\",\"client\":\"H5\"}")
    @RequestMapping(value = "/testAdd", method = {RequestMethod.POST})
    public InvocationResult testAdd(@RequestBody InvokationParameter parameter) {
        logger.info("method stats start!," + parameter.toString());
        InvocationResult invocationResult = null;
        String response = "";
        try {
            //参数校验  略...

            MinePool minePool = parameter.getJsonParamObject(MinePool.class);
            minePool.setId(null);
            minePool = minePoolRepository.save(minePool);
            logger.info("write mongodb minePoll id:" + minePool.getId());
            logger.info("write mongodb minePoll data:" + JasonUtil.toJson(minePool));
            invocationResult = new InvocationResult(true, minePool);
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




    @ApiOperation(value = "DELETE", position = 2, notes = "{\"param\": \"{\\\"id\\\":\\\"59afb9946ec03999f44393b1\\\"}\",\"channel\":\"ETH\",\"client\":\"H5\"}")
    @RequestMapping(value = "/testDel", method = {RequestMethod.POST})
    public InvocationResult testDel(@RequestBody InvokationParameter parameter) {
        logger.info("method stats start!," + parameter.toString());
        InvocationResult invocationResult = null;
        String response = "";
        try {
            //参数校验  略...

            MinePool minePool = parameter.getJsonParamObject(MinePool.class);
            minePoolRepository.delete(minePool);
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

    @ApiOperation(value = "QUERY ALL", position = 3, notes = "{\"param\": \"{\\\"id\\\":\\\"59afb9946ec03999f44393b1\\\"}\",\"channel\":\"ETH\",\"client\":\"H5\"}")
    @RequestMapping(value = "/testQuery", method = {RequestMethod.POST})
    public InvocationResult testQuery(@RequestBody InvokationParameter parameter) {
        logger.info("method stats start!," + parameter.toString());
        InvocationResult invocationResult = null;
        try {
            //参数校验  略...

            MinePool minePool = parameter.getJsonParamObject(MinePool.class);
            List<MinePool> listMinePool = minePoolRepository.findAll(Example.of(minePool));

            if (null != listMinePool && listMinePool.size() > 0)
                logger.info("MinePool:" + JasonUtil.toJson(listMinePool.get(0)));

            invocationResult = new InvocationResult(true, listMinePool);
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




    @ApiOperation(value = "UPDATE", position = 4, notes = "{\"param\": \"{\\\"id\\\":\\\"59afb9946ec03999f44393b1\\\",\\\"nameZh\\\":\\\"ETH-ZH\\\",\\\"nameCn\\\":\\\"ETH\\\",\\\"remark\\\":\\\"testtest\\\",\\\"minePoolApiList\\\":[{\\\"key\\\":\\\"STATS\\\",\\\"url\\\":\\\"http://www.ibichi.com:8080/api/stats\\\",\\\"remark\\\":\\\"aaaaa\\\"}],\\\"enable\\\":\\\"1\\\"}\",\"channel\":\"ETH\",\"client\":\"H5\"}")
    @RequestMapping(value = "/testUpdate", method = {RequestMethod.POST})
    public InvocationResult testUpdate(@RequestBody InvokationParameter parameter) {
        logger.info("method stats start!," + parameter.toString());
        InvocationResult invocationResult = null;
        String response = "";
        try {
            //参数校验  略...
            MinePool minePool = parameter.getJsonParamObject(MinePool.class);
            logger.info("update before mongodb minePoll data:" + JasonUtil.toJson(minePool));
            minePool = minePoolRepository.save(minePool);
            logger.info("update after mongodb minePoll data:" + JasonUtil.toJson(minePool));
            invocationResult = new InvocationResult(true, minePool);
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













    @ApiOperation(value = "TEST DEMO", position = 10, notes = "")
    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public InvocationResult test() {
        logger.info("method stats start!," );
        InvocationResult invocationResult = null;
        String response = "";
        try {
            MinePool minePool = new MinePool();


            minePool.setEnable("1");
            minePool.setNameCn("ETH");
            minePool.setNameZh("ETH-ZH");
            minePool.setRemark("testtest");


            MinePoolApi minePoolApi = new MinePoolApi();
            minePoolApi.setKey("" + MinePoolApi.KEY.STATS);
            minePoolApi.setUrl("http://www.ibichi.com:8080/api/stats");
            minePoolApi.setRemark("aaaaa");

            List<MinePoolApi> minePoolApiList = new ArrayList<>();
            minePoolApiList.add(minePoolApi);
            minePool.setMinePoolApiList(minePoolApiList);

            minePool = minePoolRepository.save(minePool);

            invocationResult = new InvocationResult(true, minePool);

            logger.info("write mongodb minePoll id:" + minePool.getId());
            logger.info("write mongodb minePoll data:" + JasonUtil.toJson(minePool));

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
