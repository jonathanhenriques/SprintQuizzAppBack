package com.etec.tcc.sprint_quiz.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.etec.tcc.sprint_quiz.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * Classe responsável por fazer a Codificação e a Decodificação do token
 * 
 * @author hsjon
 */
//@ProdProducao
@Service
public class JwtService {

	@Value("${security.jwt.expiracao}") // valor vindo do application.properties
	private String expiracao;
//	private String expiracao = "30";

	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
//	private String chaveAssinatura = "bWV1IGdhdG8gc2UgY2hhbWEgbWFkcnVndWluaGE=";

	/**
	 * Recebe o usuário, lê as informações e cria um token com: identificação do
	 * usuário Tempo de expiração e chave de assinatura secreta definida por uma
	 * frase em base64
	 * 
	 * @param usuario que solicitou login
	 * @return Um tokem String criptografado com um algoritmo e chave de assinatura,
	 *         contendo um subject(informação do usuario), data de expiração e as
	 *         claims
	 */
	public String gerarToken(Usuario usuario) {
//		System.out.println(" getAuthority - " + usuario.getAuthorities());
		System.out.println("getRole  - " + usuario.getRoles());
		System.out.println("expiracao - " + expiracao);
		long expiracaoString = Long.valueOf(expiracao);
		System.out.println("espiracapString - " + expiracaoString);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expiracaoString); // pega a data de agora e
																							// acrescenta o tempo da
																							// variavel "expiracao"
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant(); // aqui pegamos o momento atual
		Date data = Date.from(instant); // aqui temos a data gerada
		System.out.println("data - " + data);
		
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("nome", usuario.getNome());
		claims.put("sub", usuario.getUsername());
		claims.put("exp", data);
//		if (usuario.getFoto() != null)
//			claims.put("linkFoto", usuario.getFoto());
//		if (!usuario.getAuthorities().isEmpty())
//			claims.put("authorities", usuario.getAuthorities());

		// quebrando, arrumar depois
//		int i = 0;
//		for(RolesModel role : usuario.getRoles()) {
//			i++;
//			claims.put("role"+ i, role.getAuthority());
//		}
//		if(usuario.getTipo() != null)
//			claims.put("tipo de usuario", usuario.getTipo());
		return Jwts.builder().setSubject(usuario.getUsername())// identificacao do usuario
				.setExpiration(data)
				.setClaims(claims) // informacoes diversas 
				.signWith(SignatureAlgorithm.HS512, chaveAssinatura)// usa algoritmo escolhido HS512 e chave de
																	// assinatura para codificar o token
				.compact();

	}

	/**
	 * Extrai as claims (informações) contidas no token usando a chave de assinatura
	 * para decodificar
	 * 
	 * @param token criptografado do usuário
	 * @return as Claims passadas no token
	 * @throws ExpiredJwtException
	 */
	private Claims obterClaims(String token) throws ExpiredJwtException {
		System.out.println(Jwts.parser().setSigningKey(chaveAssinatura).parseClaimsJws(token).getBody());
		return Jwts.parser().setSigningKey(chaveAssinatura).parseClaimsJws(token).getBody();
	}

	/**
	 * Recebe o token do usuário e verifica se é válido ou não
	 * 
	 * @param token criptografado do usuário
	 * @return true se, e somente se for válido e não expirado, e false se for
	 *         inválido e já tiver expirado.
	 */
	public boolean tokenValido(String token) {
		try {
			
			Claims claims = obterClaims(token);
			Date dataExpiracao = claims.getExpiration();
			System.out.println("getExpiration() - " + claims.getExpiration());
			LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(); // convertemos
																												// de
																												// Date
																												// para
																												// LocalDateTime
			return !LocalDateTime.now().isAfter(data); // verificamos se a hora atual NÃO é depois da hora de expiracao
														// do token
		} catch (Exception e) {
			System.out.println("excecao...");
			return false;
		}
	}

	/**
	 * Extrai das claims a informação de login do usuário
	 * 
	 * @param token criptografado do usuário
	 * @return login do usuário
	 * @throws ExpiredJwtException
	 */
	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return (String) obterClaims(token).getSubject();
	}

}
