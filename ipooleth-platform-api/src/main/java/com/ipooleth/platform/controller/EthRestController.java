package com.ipooleth.platform.controller;

import com.ipooleth.common.utils.JasonUtil;
import com.ipooleth.common.utils.StringUtil;
import com.ipooleth.common.utils.common.ErrorException;
import com.ipooleth.common.utils.common.InvocationResult;
import com.ipooleth.jpa.domain.MinePool;
import com.ipooleth.jpa.repository.MinePoolRepository;
import com.ipooleth.platform.services.EthService;
import com.ipooleth.platform.util.Constansts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/rest/api")
@Api(description = "rest api", tags = "API")
public class EthRestController {

    private static final Logger logger = LogManager.getLogger(WebController.class);


    @Autowired
    private EthService ethService;


    @Autowired
    private MinePoolRepository minePoolRepository;


    /**
     * 矿池状态信息
     *
     * @param
     * @return
     */
    @ApiOperation("STATS")
    @RequestMapping(value ="/{client}/{channel}/stats", method = {RequestMethod.GET,RequestMethod.OPTIONS})
    public InvocationResult stats(@PathVariable String client, @PathVariable String channel) {
        logger.info("method stats start!,");
        InvocationResult invocationResult = null;
        String response = "";
        try {
            if (StringUtil.isBlank(client) || StringUtil.isBlank(channel)) {
                throw new ErrorException("error.eth.parameter");
            }else
            {
                List<MinePool> list = minePoolRepository.findByNameCn(channel);
                if(null==list || list.size()==0)
                {
                    throw new ErrorException("error.eth.parameter");
                }
            }
            response = ethService.stats(channel);
            Map<String,Object> result = JasonUtil.jsonToMap(response);
            invocationResult = new InvocationResult(true, result);
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


    /**
     * 矿池矿工信息
     *
     * @param
     * @return
     */
    @ApiOperation("MINERS")
    @RequestMapping(value ="/{client}/{channel}/miners", method = {RequestMethod.GET,RequestMethod.OPTIONS})
    public InvocationResult miners(@PathVariable String client, @PathVariable String channel) {
        logger.info("method miners start!,");
        String response = "";
        InvocationResult invocationResult = null;
        try {
            if (StringUtil.isBlank(client) || StringUtil.isBlank(channel)) {
                throw new ErrorException("error.eth.parameter");
            }else
            {
                List<MinePool> list = minePoolRepository.findByNameCn(channel);
                if(null==list || list.size()==0)
                {
                    throw new ErrorException("error.eth.parameter");
                }
            }
            response = ethService.miners(channel);
            Map<String,Object> result = JasonUtil.jsonToMap(response);
            invocationResult = new InvocationResult(true, result);
        } catch (Exception e) {
            if (e instanceof ErrorException) {
                invocationResult = new InvocationResult(false, ((ErrorException) e).getErrcode(), ((ErrorException) e).getErrm(), "");
            } else {
                ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
                invocationResult = new InvocationResult(false, ex.getErrcode(), ex.getErrm(), "");
                logger.error("method miners  exception!", e);
            }

        }
        logger.info("method miners end!");
        return invocationResult;
    }


    /**
     * 矿池区块信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "BLOCKS")
    @RequestMapping(value ="/{client}/{channel}/blocks", method = {RequestMethod.GET,RequestMethod.OPTIONS})
    public InvocationResult blocks(@PathVariable String client,@PathVariable String channel) {
        logger.info("method blocks start!,");
        String response = "";
        InvocationResult invocationResult = null;
        try {
            if (StringUtil.isBlank(client) || StringUtil.isBlank(channel)) {
                throw new ErrorException("error.eth.parameter");
            }else
            {
                List<MinePool> list = minePoolRepository.findByNameCn(channel);
                if(null==list || list.size()==0)
                {
                    throw new ErrorException("error.eth.parameter");
                }
            }
            response = ethService.blocks(channel);
            Map<String,Object> result = JasonUtil.jsonToMap(response);
            invocationResult = new InvocationResult(true, result);
        } catch (Exception e) {
            if (e instanceof ErrorException) {
                invocationResult = new InvocationResult(false, ((ErrorException) e).getErrcode(), ((ErrorException) e).getErrm(), "");
            } else {
                ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
                invocationResult = new InvocationResult(false, ex.getErrcode(), ex.getErrm(), "");
                logger.error("method blocks  exception!", e);
            }

        }
        logger.info("method blocks end!");
        return invocationResult;
    }


    /**
     * 矿池支付信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "PAYMENTS")
    @RequestMapping(value ="/{client}/{channel}/payments", method = {RequestMethod.GET,RequestMethod.OPTIONS})
    public InvocationResult payments(@PathVariable String client,@PathVariable String channel) {
        logger.info("method payments start!,");
        InvocationResult invocationResult = null;
        String response = "";
        try {
            if (StringUtil.isBlank(client) || StringUtil.isBlank(channel)) {
                throw new ErrorException("error.eth.parameter");
            }else
            {
                List<MinePool> list = minePoolRepository.findByNameCn(channel);
                if(null==list || list.size()==0)
                {
                    throw new ErrorException("error.eth.parameter");
                }
            }
            response = ethService.payments(channel);
            Map<String,Object> result = JasonUtil.jsonToMap(response);
            invocationResult = new InvocationResult(true, result);
        } catch (Exception e) {
            if (e instanceof ErrorException) {
                invocationResult = new InvocationResult(false, ((ErrorException) e).getErrcode(), ((ErrorException) e).getErrm(), "");
            } else {
                ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
                invocationResult = new InvocationResult(false, ex.getErrcode(), ex.getErrm(), "");
                logger.error("method payments  exception!", e);
            }

        }
        logger.info("method payments end!");
        return invocationResult;
    }


    /**
     * 矿池个人信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "ACCOUNTS")
    @RequestMapping(value ="/{client}/{channel}/accounts/{account}", method = {RequestMethod.GET,RequestMethod.OPTIONS})
    public InvocationResult accounts(@PathVariable String client,@PathVariable String channel,@PathVariable String account){
        logger.info("method accounts start!,");
        InvocationResult invocationResult = null;
        String response = "";
        try {
            if (StringUtil.isBlank(client) || StringUtil.isBlank(channel)|| StringUtil.isBlank(account)) {
                throw new ErrorException("error.eth.parameter");
            }else
            {

                String regEx = "0x[0-9a-fA-F]{40}";
                Pattern pattern = Pattern.compile(regEx);
                Matcher matcher = pattern.matcher(account);
                if(!matcher.matches())
                {
                    throw new ErrorException("error.eth.moneyaddre_not_valid");
                }
                List<MinePool> list = minePoolRepository.findByNameCn(channel);
                if(null==list || list.size()==0)
                {
                    throw new ErrorException("error.eth.parameter");
                }

            }
            //当前矿机情况
            response = ethService.accounts(channel,account);
            Map<String,Object> result = JasonUtil.jsonToMap(response);
            
            //24小时内的离线矿机情况
            response = ethService.historyAccounts(channel,account.toLowerCase());
            Map<String,Object> resultSub = JasonUtil.jsonToMap(response);
            for (Map.Entry entity:resultSub.entrySet())
            {
                Map<String,Object> map_= JasonUtil.jsonToMap(String.valueOf(entity.getValue()));
                Object objAccount= map_.get("workers");
                if (null!=objAccount && objAccount instanceof Map)
                {
                    Map<String,Object> workersMap= (Map<String, Object>) objAccount;
                    for (Map.Entry entry_:workersMap.entrySet()){
                        Object workersStatsMap = entry_.getValue();
                        if(null!=workersStatsMap && workersStatsMap instanceof  Map)
                        {
                            Object offline = ((Map)workersStatsMap).get("offline");
                            //该矿机是否在线
                            if(String.valueOf(offline).equals("true"))
                            {
                                Object workers= result.get("workers");
                                if (null!=workers && workers instanceof Map)
                                {
                                    ((Map) workers).put(entry_.getKey(),entry_.getValue());
                                }

                            }
                        }
                    }
                }
            }
            invocationResult = new InvocationResult(true, result);
        } catch (Exception e) {
            if (e instanceof ErrorException) {
                invocationResult = new InvocationResult(false, ((ErrorException) e).getErrcode(), ((ErrorException) e).getErrm(), "");
            } else {
                ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
                invocationResult = new InvocationResult(false, ex.getErrcode(), ex.getErrm(), "");
                logger.error("method historyStats  exception!", e);
            }
        }
        logger.info("method accounts end!");
        return invocationResult;
    }


    /**
     * 矿池24H状态信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "HISTORYSTATS")
    @RequestMapping(value ="/{client}/{channel}/historyStats", method = {RequestMethod.GET,RequestMethod.OPTIONS})
    public InvocationResult historyStats(@PathVariable String client,@PathVariable String channel) {
        logger.info("method historyStats start!,");
        String response = "";
        InvocationResult invocationResult = null;
        try {
            if (StringUtil.isBlank(client) || StringUtil.isBlank(channel)) {
                throw new ErrorException("error.eth.parameter");
            }else
            {
                List<MinePool> list = minePoolRepository.findByNameCn(channel);
                if(null==list || list.size()==0)
                {
                    throw new ErrorException("error.eth.parameter");
                }
            }
            response = ethService.historyStats(channel);
            Map<String,Object> result = JasonUtil.jsonToMap(response);
            for (Map.Entry entity:result.entrySet())
            {
                Map<String,Object> map_= JasonUtil.jsonToMap(String.valueOf(entity.getValue()));
                entity.setValue(map_);
            }
            invocationResult = new InvocationResult(true, result);
        } catch (Exception e) {
            if (e instanceof ErrorException) {
                invocationResult = new InvocationResult(false, ((ErrorException) e).getErrcode(), ((ErrorException) e).getErrm(), "");
            } else {
                ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
                invocationResult = new InvocationResult(false, ex.getErrcode(), ex.getErrm(), "");
                logger.error("method historyStats  exception!", e);
            }

        }
        logger.info("method historyStats end!");
        return invocationResult;
    }


    /**
     * 矿工24H状态信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "HISTORYACCOUNTS")
    @RequestMapping(value ="/{client}/{channel}/historyAccounts/{account}", method = {RequestMethod.GET,RequestMethod.OPTIONS})
    public InvocationResult historyAccounts(@PathVariable String client,@PathVariable String channel,@PathVariable String account){
        logger.info("method historyAccounts start!,");
        String response = "";
        InvocationResult invocationResult = null;
        try {
            if (StringUtil.isBlank(client) || StringUtil.isBlank(channel) ||StringUtil.isBlank(account) ) {
                throw new ErrorException("error.eth.parameter");
            }else
            {
                String regEx = "0x[0-9a-fA-F]{40}";
                Pattern pattern = Pattern.compile(regEx);
                Matcher matcher = pattern.matcher(account);
                if(!matcher.matches())
                {
                    throw new ErrorException("error.eth.moneyaddre_not_valid");
                }

                List<MinePool> list = minePoolRepository.findByNameCn(channel);
                if(null==list || list.size()==0)
                {
                    throw new ErrorException("error.eth.parameter");
                }
            }
            response = ethService.historyAccounts(channel,account.toLowerCase());
            Map<String,Object> result = JasonUtil.jsonToMap(response);
            for (Map.Entry entity:result.entrySet())
            {
                Map<String,Object> map_= JasonUtil.jsonToMap(String.valueOf(entity.getValue()));
                entity.setValue(map_);
            }
            invocationResult = new InvocationResult(true, result);
        } catch (Exception e) {
            if (e instanceof ErrorException) {
                invocationResult = new InvocationResult(false, ((ErrorException) e).getErrcode(), ((ErrorException) e).getErrm(), "");
            } else {
                ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
                invocationResult = new InvocationResult(false, ex.getErrcode(), ex.getErrm(), "");
                logger.error("method historyAccounts  exception!", e);
            }

        }
        logger.info("method historyAccounts end!");
        return invocationResult;
    }




}
