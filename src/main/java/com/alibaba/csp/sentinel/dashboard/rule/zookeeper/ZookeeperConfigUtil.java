/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule.zookeeper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ZookeeperConfigUtil {
    public static String RULE_ROOT_PATH;
    @Value("${sentinel.dashboard.zookeeper.ruleRootPath:/sentinel_rule_config}")
    private String ruleRootPath;
    
    public static int RETRY_TIMES;
    @Value("${sentinel.dashboard.zookeeper.rule.retryTimes:2}")
    private int retryTimes;
    
    public static int SLEEP_TIME;
    @Value("${sentinel.dashboard.zookeeper.rule.sleetTime:1000}")
    private int sleetTime;

    @PostConstruct
    public void init() {
        RULE_ROOT_PATH = ruleRootPath;
        RETRY_TIMES = retryTimes;
        SLEEP_TIME = sleetTime;
    }

    public static String getPath(String appName) {

        StringBuilder stringBuilder = new StringBuilder(RULE_ROOT_PATH);

        if (StringUtils.isBlank(appName)) {
            return stringBuilder.toString();
        }
        if (appName.startsWith("/")) {
            stringBuilder.append(appName);
        } else {
            stringBuilder.append("/").append(appName);
        }
        return stringBuilder.toString();
    }
}