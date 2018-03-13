package com.ipooleth.platform.controller;

import com.ipooleth.common.utils.StringUtil;
import com.ipooleth.common.utils.common.ErrorException;
import com.ipooleth.common.utils.common.InvocationResult;
import com.ipooleth.common.utils.common.InvokationParameter;
import com.ipooleth.jpa.domain.MinePool;
import com.ipooleth.jpa.repository.MinePoolRepository;
import com.ipooleth.platform.services.EthService;
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

import java.util.List;
import java.util.Map;

/**
 * 矿池接口
 *
 */
@Api(tags="API",description ="ETH MINEPOOL",hidden = true)
@RestController
@RequestMapping("/api")
public class EthController {
	private static final Logger logger = LogManager.getLogger(EthController.class);

	@Autowired
	private EthService ethService;


	@Autowired
	private MinePoolRepository minePoolRepository;



	/**
	 * 矿池状态信息
	 *
	 * @param parameter
	 * @return
	 */
	@ApiOperation(value = "STATS", position = 1, notes = "{\"param\": \"\",\"channel\":\"ETH\",\"client\":\"H5\"}")
	@RequestMapping(value = "/stats", method = {RequestMethod.POST})
	public InvocationResult stats(@RequestBody InvokationParameter parameter) {
		logger.info("method stats start!,"+parameter.toString());
		InvocationResult invocationResult=null;
		String  response = "";
		try {
			checkParameterpr(parameter);
			response = ethService.stats(parameter.getChannel());
			invocationResult = new InvocationResult(true,response);
		} catch (Exception e) {
			if(e instanceof ErrorException) {
				invocationResult = new InvocationResult(false,((ErrorException) e).getErrcode(),((ErrorException) e).getErrm(), "");
			}else
			{
				ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
				invocationResult = new InvocationResult(false,ex.getErrcode(),ex.getErrm(), "");
				logger.error("method stats  exception!",e);
			}

		}
		logger.info("method stats end!");
		return invocationResult;
	}


	/**
	 * 矿池矿工信息
	 *
	 * @param parameter
	 * @return
	 */
	@ApiOperation(value = "MINERS", position = 1, notes = "{\"param\": \"\",\"channel\":\"ETH\",\"client\":\"H5\"}")
	@RequestMapping(value = "/miners", method = {RequestMethod.POST})
	public InvocationResult miners(@RequestBody InvokationParameter parameter) {
		logger.info("method miners start!,"+parameter.toString());
		String  response = "";
		InvocationResult invocationResult=null;
		try {
			checkParameterpr(parameter);
			response = ethService.miners(parameter.getChannel());
			invocationResult = new InvocationResult(true,response);
		} catch (Exception e) {
			if(e instanceof ErrorException) {
				invocationResult = new InvocationResult(false,((ErrorException) e).getErrcode(),((ErrorException) e).getErrm(), "");
			}else
			{
				ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
				invocationResult = new InvocationResult(false,ex.getErrcode(),ex.getErrm(), "");
				logger.error("method miners  exception!",e);
			}

		}
		logger.info("method miners end!");
		return invocationResult;
	}


	/**
	 * 矿池区块信息
	 *
	 * @param parameter
	 * @return
	 */
	@ApiOperation(value = "BLOCKS", position = 1, notes = "{\"param\": \"\",\"channel\":\"ETH\",\"client\":\"H5\"}")
	@RequestMapping(value = "/blocks", method = {RequestMethod.POST})
	public InvocationResult blocks(@RequestBody InvokationParameter parameter) {
		logger.info("method blocks start!,"+parameter.toString());
		String  response = "";
		InvocationResult invocationResult=null;
		try {
			checkParameterpr(parameter);
			response = ethService.blocks(parameter.getChannel());
			invocationResult = new InvocationResult(true,response);
		} catch (Exception e) {
			if(e instanceof ErrorException) {
				invocationResult = new InvocationResult(false,((ErrorException) e).getErrcode(),((ErrorException) e).getErrm(), "");
			}else
			{
				ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
				invocationResult = new InvocationResult(false,ex.getErrcode(),ex.getErrm(), "");
				logger.error("method blocks  exception!",e);
			}

		}
		logger.info("method blocks end!");
		return invocationResult;
	}


	/**
	 * 矿池支付信息
	 *
	 * @param parameter
	 * @return
	 */
	@ApiOperation(value = "PAYMENTS", position = 1, notes = "{\"param\": \"\",\"channel\":\"ETH\",\"client\":\"H5\"}")
	@RequestMapping(value = "/payments", method = {RequestMethod.POST})
	public InvocationResult payments(@RequestBody InvokationParameter parameter) {
		logger.info("method payments start!,"+parameter.toString());
		InvocationResult invocationResult=null;
		String  response = "";
		try {
			checkParameterpr(parameter);
			response = ethService.payments(parameter.getChannel());
			invocationResult = new InvocationResult(true,response);
		} catch (Exception e) {
			if(e instanceof ErrorException) {
				invocationResult = new InvocationResult(false,((ErrorException) e).getErrcode(),((ErrorException) e).getErrm(), "");
			}else
			{
				ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
				invocationResult = new InvocationResult(false,ex.getErrcode(),ex.getErrm(), "");
				logger.error("method payments  exception!",e);
			}

		}
		logger.info("method payments end!");
		return invocationResult;
	}


	/**
	 * 矿池个人信息
	 *
	 * @param parameter
	 * @return
	 */
	@ApiOperation(value = "ACCOUNTS", position = 1, notes = "{\"param\": \"{\\\"accounts\\\":\\\"59afb9946ec03999f44393b1\\\"\",\"channel\":\"ETH\",\"client\":\"H5\"}")
	@RequestMapping(value = "/accounts", method = {RequestMethod.POST})
	public InvocationResult accounts(@RequestBody InvokationParameter parameter) throws Exception {
		logger.info("method accounts start!,"+parameter.toString());
		InvocationResult invocationResult=null;
		String  response = "";
		checkParameterpr(parameter);
		Map<String,Object> map =parameter.getJsonParamMap();
		Object accounts = map.get("accounts");
		if(null==accounts)
		{
			throw new ErrorException("error.eth.accounts_is_null");
		}
		response = ethService.accounts(parameter.getChannel(),String.valueOf(accounts));
		invocationResult = new InvocationResult(true,response);
		logger.info("method accounts end!");
		return invocationResult;
	}


	/**
	 * 矿池24H状态信息
	 *
	 * @param parameter
	 * @return
	 */
	@ApiOperation(value = "HISTORYSTATS", position = 1, notes = "{\"param\": \"\",\"channel\":\"ETH\",\"client\":\"H5\"}")
	@RequestMapping(value = "/historyStats", method = {RequestMethod.POST})
	public InvocationResult historyStats(@RequestBody InvokationParameter parameter) {
		logger.info("method historyStats start!,"+parameter.toString());
		String  response = "";
		InvocationResult invocationResult = null;
		try {
			checkParameterpr(parameter);
			response = ethService.historyStats(parameter.getChannel());
			invocationResult = new InvocationResult(true,response);
		} catch (Exception e) {
			if(e instanceof ErrorException) {
				invocationResult = new InvocationResult(false,((ErrorException) e).getErrcode(),((ErrorException) e).getErrm(), "");
			}else
			{
				ErrorException ex = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
				invocationResult = new InvocationResult(false,ex.getErrcode(),ex.getErrm(), "");
				logger.error("method historyStats  exception!",e);
			}

		}
		logger.info("method historyStats end!");
		return invocationResult;
	}


	private void checkParameterpr(InvokationParameter parameter) throws ErrorException{
		if(StringUtil.isBlank(parameter.getChannel()))
		{
			throw new ErrorException("error.eth.channel_is_null");
		}else
		{
			List<MinePool> list = minePoolRepository.findByNameCn(parameter.getChannel());
			if(null==list || list.size()==0)
			{
				throw new ErrorException("error.eth.parameter");
			}
		}
		if(StringUtil.isBlank(parameter.getClient()))
		{
			throw new ErrorException("error.eth.client_is_null");
		}
	}




}
