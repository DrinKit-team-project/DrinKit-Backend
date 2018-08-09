package com.teamproject.drinkit.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class SuitableUrlMatcher implements RequestMatcher {

    private OrRequestMatcher skipRequestMatcher;
    private RequestMatcher notToSkipRequestMatcher;
    private static final Logger log = LoggerFactory.getLogger(SuitableUrlMatcher.class);

    public SuitableUrlMatcher(@Value("${skipUrls}") String pathsToSkip, @Value("${noToSkipPath}") String processingPath){
        this.skipRequestMatcher = new OrRequestMatcher(Arrays.asList(pathsToSkip).stream().map(p -> new AntPathRequestMatcher(p)).collect(Collectors.toList()));
        this.notToSkipRequestMatcher = new AntPathRequestMatcher(processingPath);
    }

    @Override
    public boolean matches(HttpServletRequest req) {
        return !skipRequestMatcher.matches(req)&&notToSkipRequestMatcher.matches(req);
    }
}
