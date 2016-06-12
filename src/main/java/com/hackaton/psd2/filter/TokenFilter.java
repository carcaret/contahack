package com.hackaton.psd2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hackaton.psd2.dao.model.CredentialInfo;
import com.hackaton.psd2.dao.repository.CredentialInfoRepository;
import com.hackaton.psd2.security.TokenMap;
import com.hackaton.psd2.security.UserTokenMgr;

public class TokenFilter implements Filter {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final TokenMap tokenMap;

	private CredentialInfoRepository bean;

	public TokenFilter(TokenMap tokenMap) {
		this.tokenMap = tokenMap;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		bean = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext())
				.getBean(CredentialInfoRepository.class);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) servletRequest;
		String user = "";
		if (req.getUserPrincipal() != null) {
			user = req.getUserPrincipal().getName();
		}
		try {
			if (!user.isEmpty() && tokenMap.getUserToken(user) == null) {
				Iterable<CredentialInfo> findAll = bean.findAll();
				CredentialInfo findOne = bean.findOneByUid(user);

				tokenMap.setUserToken(user, UserTokenMgr.getUserToken(findOne != null ?findOne.getUserRemote() : "cesinrm@gmail.com", findOne != null ?findOne.getPassRemote() : "falcons666"));
			}
		} catch (Exception e) {
			log.error(String.format("No se pudo recuperar el token para el usuario %s", user), e);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
	}
}
