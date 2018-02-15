package br.edu.ufcg.labsi.tokenFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TokenFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String header = request.getHeader("Authorization");

        System.out.println(header);

        if (header == null) {
            throw new ServletException("Token inexistente ou invalido");
        }

        String token = header;

        try {
            Jwts.parser().setSigningKey("chocolate").parseClaimsJws(token).getBody();
        } catch (SignatureException e) {
            throw new ServletException("token invalido");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
