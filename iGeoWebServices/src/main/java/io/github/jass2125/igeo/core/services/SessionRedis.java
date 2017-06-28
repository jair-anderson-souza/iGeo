/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.igeo.core.services;

import redis.clients.jedis.Jedis;

/**
 *
 * @author <a href="mailto:jair_anderson_bs@hotmail.com">Anderson Souza</a>
 * @since Jun 28, 2017 2:31:09 PM
 */
public class SessionRedis {

    private String host;
    private int port;
    private Jedis jedis;

    public SessionRedis() {
        this.host = "localhost";
        this.port = 6379;
        this.jedis = new Jedis(host, port);
    }

    public boolean createKey(String key, String value) {
        String set = jedis.set(key, value);
        jedis.expire(key, 1800);
        return set.equalsIgnoreCase("OK");
    }

    public void updateTimeOut(String key) {
        jedis.expire(key, 1800);
    }

    public String getKey(String key) {
        if (key != null) {
            String keyTemp = jedis.get(key);
            if (keyTemp != null) {
                return keyTemp;
            }
        }
        return "";
    }

    public boolean deleteKey(String key) {
        jedis.del(key);
        return true;
    }
}
