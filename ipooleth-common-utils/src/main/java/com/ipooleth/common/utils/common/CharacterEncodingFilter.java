package com.ipooleth.common.utils.common;

import com.ipooleth.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;

@WebFilter(
		urlPatterns = "/*",
		filterName = "CharacterEncodingFilter",
		initParams = {
				@WebInitParam(name = "encoding", value = "UTF-8"),
				@WebInitParam(name = "forceEncoding", value = "true"),
				@WebInitParam(name = "mappingURL", value = "*Controller")
		}
)
public class CharacterEncodingFilter extends org.springframework.web.filter.CharacterEncodingFilter{
	
	private static final Logger log = LoggerFactory.getLogger("LOGISTICS-COMPONENT");
	
	private String encoding;
	private String mappingURL;
	private boolean forceEncoding = false;

	/**
	 * @param mappingURL the mappingURL to set
	 */
	public void setMappingURL(String mappingURL) {
		this.mappingURL = mappingURL;
	}

	/**
	 * Set the encoding to use for requests. This encoding will be passed into a
	 * {@link HttpServletRequest#setCharacterEncoding} call.
	 * <p>Whether this encoding will override existing request encodings
	 * (and whether it will be applied as default response encoding as well)
	 * depends on the {@link #setForceEncoding "forceEncoding"} flag.
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * Set whether the configured {@link #setEncoding encoding} of this filter
	 * is supposed to override existing request and response encodings.
	 * <p>Default is "false", i.e. do not modify the encoding if
	 * {@link HttpServletRequest#getCharacterEncoding()}
	 * returns a non-null value. Switch this to "true" to enforce the specified
	 * encoding in any case, applying it as default response encoding as well.
	 * <p>Note that the response encoding will only be set on Servlet 2.4+
	 * containers, since Servlet 2.3 did not provide a facility for setting
	 * a default response encoding.
	 */
	public void setForceEncoding(boolean forceEncoding) {
		this.forceEncoding = forceEncoding;
	}


	@Override
	protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		ServletRequest requestWrapper = null;

		String url=request.getRequestURL().toString();

		log.info("url:"+url);
//		if(url.matches(mappingURL)){  
		//TODO
		if(true){    
			if(request instanceof HttpServletRequest) {
	            requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request,encoding);
	        } 
		}
		
		if (this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)) {
			request.setCharacterEncoding(this.encoding);
			if (this.forceEncoding) {
				response.setCharacterEncoding(this.encoding);
			}
		}
		
		if(null == requestWrapper) {  
			
			filterChain.doFilter(request, response);  
        } else {  
        	BufferedInputStream in = new BufferedInputStream(requestWrapper.getInputStream());
    		
    		String poreadate = StringUtil.readByteString(in,encoding);
    		
    		log.info("after poreadate:"+poreadate);
        	filterChain.doFilter(requestWrapper, response);  
        }  
			
	}
}
