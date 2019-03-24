package com.sdc.factor.common.beans;

/**
 * 简单键值对
 * 
 * @author sean
 * @since 2019-03-24
 *
 */
public class KV {

    private String k;

    private String v;

    public KV() {
    }

    public KV(String k, String v) {
        this.k = k;
        this.v = v;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

}
