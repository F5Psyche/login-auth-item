package com.hf.auth.entity.yml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhanghf
 * @version 1.0
 * @date 18:01 2022/9/13
 */
@Component
@ConfigurationProperties(prefix = "exclude", ignoreInvalidFields = true)
public class ExcludeInfo implements Serializable {

    private List<String> patterns;

    List<String> domainHosts;

    public List<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<String> patterns) {
        this.patterns = patterns;
    }

    public List<String> getDomainHosts() {
        return domainHosts;
    }

    public void setDomainHosts(List<String> domainHosts) {
        this.domainHosts = domainHosts;
    }
}
