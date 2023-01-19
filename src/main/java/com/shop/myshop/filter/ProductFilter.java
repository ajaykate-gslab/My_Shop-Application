package com.shop.myshop.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ProductFilter implements Filter {



    Logger LOG= LoggerFactory.getLogger(ProductFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse res= (HttpServletResponse) response;

        LOG.info("-----------------------------------------------------------------------------------");
        LOG.info("Request information {}: {}: {}: {}",
                ((HttpServletRequest) request).getMethod(),
                request.getServerPort(),
                ((HttpServletRequest) request).getRequestURI(),
                ((HttpServletRequest) request).getRequestURL()
                );
        chain.doFilter(req,res);

        LOG.info("-----------------------------------------------------------------------------------");
        LOG.info("Response information {}: {} ",
                response.getContentType(),
                ((HttpServletResponse) response).getStatus());

        LOG.info("-----------------------------------------------------------------------------------");


    }


}
