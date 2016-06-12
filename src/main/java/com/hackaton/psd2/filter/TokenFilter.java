package com.hackaton.psd2.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.filter.GenericFilterBean;

import com.hackaton.psd2.dao.model.CredentialInfo;
import com.hackaton.psd2.dao.repository.CredentialInfoRepository;
import com.hackaton.psd2.security.UserTokenMgr;
import com.hackaton.psd2.security.impl.TokenMapImpl;

public class TokenFilter extends GenericFilterBean {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final TokenMapImpl tokenMap;

	@Autowired
	private CredentialInfoRepository bean;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserTokenMgr userTokenMgrImpl;

	public TokenFilter(TokenMapImpl tokenMap) {
		this.tokenMap = tokenMap;
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
				CredentialInfo findOne = bean.findOneByUid(user);

//				if (findOne == null)
//					findOne = jdbcTemplate.queryForObject("SELECT * FROM crentialInfo WHERE UID=\"" + user + "\";", CredentialInfo.class);

				tokenMap.setUserToken(user,
						userTokenMgrImpl.getUserToken(findOne != null ? findOne.getUserRemote() : "cesinrm@gmail.com",
								findOne != null ? findOne.getPassRemote() : "falcons666"));
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
