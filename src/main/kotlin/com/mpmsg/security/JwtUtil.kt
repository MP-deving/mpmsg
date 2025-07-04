package com.mpmsg.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken (id: Int): String {

        return Jwts.builder()
            .setSubject(id.toString())
            .setExpiration(Date(System.currentTimeMillis() + expiration!!))
            .signWith(SignatureAlgorithm.HS512, secret!!.toByteArray())
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        return try {
            val claims = getClaims(token)
            claims.subject != null && claims.expiration != null && Date().before(claims.expiration)
        } catch (ex: Exception) {
            false
        }
    }

    private fun getClaims(token: String): Claims {
        if (secret.isNullOrBlank()) {
            throw IllegalStateException("JWT secret is not configured.")
        }
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
        } catch (ex: Exception) {
            throw IllegalArgumentException("Invalid or expired JWT token.", ex)
        }
    }

    fun getSubject(token: String): String {
        return getClaims(token).subject
    }
}
