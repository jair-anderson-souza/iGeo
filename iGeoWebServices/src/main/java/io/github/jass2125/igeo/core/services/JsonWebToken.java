/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.sql.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.eclipse.persistence.internal.oxm.conversion.Base64;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 28, 2017 2:13:18 PM
 */
public class JsonWebToken {

    private String key;
    private SignatureAlgorithm signatureAlgorithm;
    private long nowMillis;
    private Date now;
    private byte[] apiKeySecretBytes;
    private Key signingKey;
    private JwtBuilder jwtBuilder;

    public JsonWebToken(String email) {
        this.key = email + System.currentTimeMillis();
        this.signatureAlgorithm = SignatureAlgorithm.HS512;
        this.nowMillis = System.currentTimeMillis();
        this.now = new Date(nowMillis);
        this.apiKeySecretBytes = DatatypeConverter.parseBase64Binary(this.key);
        this.signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    public String encodeResponse(String name, String object) {
        return Jwts.builder().
                setIssuedAt(now).
                setSubject(name).
                setIssuer(object).
                signWith(signatureAlgorithm, signingKey).
                compact();
    }

    public Claims decodeResponse(String jwt) {
        return Jwts.
                parser().
                setSigningKey(signingKey).
                parseClaimsJws(jwt).
                getBody();
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public long getNowMillis() {
        return nowMillis;
    }

    public void setNowMillis(long nowMillis) {
        this.nowMillis = nowMillis;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public byte[] getApiKeySecretBytes() {
        return apiKeySecretBytes;
    }

    public void setApiKeySecretBytes(byte[] apiKeySecretBytes) {
        this.apiKeySecretBytes = apiKeySecretBytes;
    }

    public Key getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(Key signingKey) {
        this.signingKey = signingKey;
    }

    public JwtBuilder getJwtBuilder() {
        return this.jwtBuilder;
    }

    public void setJwtBuilder(JwtBuilder jwtBuilder) {
        this.jwtBuilder = jwtBuilder;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        return new String(Base64.base64Encode(this.key.getBytes()));
    }

}
