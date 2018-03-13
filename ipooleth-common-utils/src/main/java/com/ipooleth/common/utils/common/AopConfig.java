package com.ipooleth.common.utils.common;


import com.ipooleth.common.utils.Constansts;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AopConfig {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut(value="@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void executeControllerMethod() {
		logger.info("AOP is working");
	}
	
	@Around("executeControllerMethod()")
	public Object aroundControllerInvoiked(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object[] args = pjp.getArgs();

		InvokationParameter param = null;

		if (null!=args && args.length>0) {
			if (args[0] instanceof InvokationParameter){
				param = (InvokationParameter) args[0];
			}
		}

		try {

			Object result_= pjp.proceed();





			return result_;
		} catch (Exception ex) {
			InvocationResult errResult = null;
			if (ex instanceof ErrorException) {
				String errorCode = ((ErrorException) ex).getErrcode();
				String errorMsg = ((ErrorException) ex).getErrm();
				errResult = new InvocationResult(false, errorCode, errorMsg, null);
			}else if (ex instanceof Exception) {
				ErrorException e = new ErrorException(Constansts.NO_DEFINE_ERROR_CODE);
				errResult = new InvocationResult(false, e.getErrcode(), e.getErrm(), null);
				logger.error("Aop Service invoking error", ex);
			}
			return errResult;
		} finally {
			Object target = pjp.getTarget();
			Logger logger = LoggerFactory.getLogger(target.getClass());
			logger.info("REST interface has been invoked: Interface Name:[" + target.getClass().getSimpleName() + "]" + ", Method Name:["+ pjp.getStaticPart().getSignature().getName() + "]" + ", Consumed Time:[" + (System.currentTimeMillis() - start) + "ms]");
		}
	}







	//----------------------spring-boot-data-rest   api  result convert  start ----------------

//	private Object dataRestConvert(Object result_)
//	{
//		if(result_ instanceof PagedResources)
//		{
//			PagedResources pr = (PagedResources)result_;
//			Iterator i  =pr.getContent().iterator();
//			List<Object> list = new ArrayList<>();
//			Object obj;
//			while (pr.getContent().size()>0&&i.hasNext())
//			{
//				obj = i.next();
//				if(obj instanceof PersistentEntityResource) {
//					PersistentEntityResource per = (PersistentEntityResource)obj;
//					list.add(per.getContent());
//				}
//			}
//			Map<String,Object> map = new HashMap<>();
//			map.put("data",list);
//			map.put("pages",pr.getMetadata());
//			result_ = new InvocationResult(true, map);
//			//result_=new PagedResources(list,pr.getMetadata());
//		}
//		else if(result_ instanceof ResponseEntity)
//		{
//			ResponseEntity re = (ResponseEntity)result_;
//			if(HttpStatus.OK.equals(re.getStatusCode())|| HttpStatus.CREATED.equals(re.getStatusCode()))
//			{
//				if(null!=re.getBody()) {
//					PersistentEntityResource per = (PersistentEntityResource) re.getBody();
//					Object content = per.getContent();
//					result_ = new InvocationResult(true, content);
//				}else
//				{
//					result_="";
//				}
//				result_ = new ResponseEntity(result_,re.getStatusCode());
//
//			}
//		}
//		return result_;
//	}
	//----------------------spring-boot-data-rest   api  result convert  end ----------------


}
