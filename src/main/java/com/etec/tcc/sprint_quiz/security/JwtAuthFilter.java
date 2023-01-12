package com.etec.tcc.sprint_quiz.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.etec.tcc.sprint_quiz.service.impl.UsuarioServiceImpl;
import com.etec.tcc.sprint_quiz.util.JwtUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Filtro que recebe a Requisição do cliente Verifica se existe um token no
 * header da requisição e se ele é válido
 * 
 * @author hsjon
 * @since 15/12/2022
 */
//@ProdProducao
@Slf4j
@Component
//@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	@Lazy
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private JwtUtils jwtUtils;
	private final int BEARER = "Bearer ".length(); // 7

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.info("filtro jwtAuthFilter chamado!");
		String authHeader = request.getHeader("Authorization");
		String userEmail;
		String jwtToken;

		if (authHeader == null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			log.info("Usuário não autorizado");
			return;
		}
		jwtToken = authHeader.substring(BEARER);
		userEmail = jwtUtils.extractUsername(jwtToken);
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = usuarioServiceImpl.loadUserByUsername(userEmail);
//					.orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado!"));

			if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
				log.info("usuário passou pelo filtro autenticado: " + authToken.isAuthenticated());
			}
		}
		filterChain.doFilter(request, response);
	}

}
