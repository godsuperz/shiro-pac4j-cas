package com.superz.jwt.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhang.chao
 * @date 2021/1/2
 */
@RestController
@RequestMapping("/jwt")
public class IndexController {

    @PostMapping("/testHttpStatus500")
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String testHttpStatus500() {
        return "😓😓😓 500 啦！";
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withIssuer("superz")
                .withClaim("username", username)
                .withClaim("password", password)
                .sign(algorithm);
        return "{\"token\":\""+token+"\"}";
    }

    @GetMapping("/getInfo")
    public String getInfo(@RequestHeader String authorization) {
        String token = authorization.split(" ")[1];
        Algorithm algorithm = Algorithm.HMAC256("secret111");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT verify = verifier.verify(token);
        System.out.println("verify = " + verify);
        return "111";
    }
}
