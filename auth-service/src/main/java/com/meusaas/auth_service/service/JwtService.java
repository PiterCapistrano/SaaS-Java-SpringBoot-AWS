package com.meusaas.auth_service.service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class JwtService {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private long expiration;

  //Gera o token JWT com base nos dados do usuário
  public String generateToken(UserDetails userDetails) throws JOSEException {
    Date now = new Date();
    Date expirationDate = new Date(now.getTime() + expiration);

    //cria o assinante HMAC usando o segredo
    JWSSigner signer = new MACSigner(secret);

    JWTClaimsSet claims = new JWTClaimsSet.Builder()
      .subject(userDetails.getUsername())
      .issueTime(now)
      .expirationTime(expirationDate)
      .build();

    SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);
    signedJWT.sign(signer);

    return signedJWT.serialize();
  }

  // Valida o token e verifica se pertence ao usuário informado
  public boolean validateToken(String token, UserDetails userDetails) {
    try {
      SignedJWT signedJWT = SignedJWT.parse(token);
      JWSVerifier verifier = new MACVerifier(secret.getBytes(StandardCharsets.UTF_8));
      if(!signedJWT.verify(verifier)) {
        return false;
      }
      String username = signedJWT.getJWTClaimsSet().getSubject();
      Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();
      return username.equals(userDetails.getUsername()) && expirationDate.after(new Date());
    } catch (Exception e) {
      return false;
    }
  }
  
  // Extrai o email (username) do token
  public String extractUsername(String token) {
    try {
      SignedJWT signedJWT = SignedJWT.parse(token);
      return signedJWT.getJWTClaimsSet().getSubject();
    } catch (ParseException e) {
      return null;
    }
  }
}
