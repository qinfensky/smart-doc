package com.power.doc.model;

/**
 * Description:
 * restful api error code
 *
 * @author yu 2018/06/25.
 */
public class ApiErrorCode {

    /**
     * error code
     */
    private String value;

    /**
     * error code type
     */
    private String type;

    /**
     * error description
     */
    private String desc;


    public String getValue() {
        return value;
    }

    public ApiErrorCode setValue(String value) {
        this.value = value;
        return this;
    }

    public String getType() {
        return type;
    }

    public ApiErrorCode setType(String type) {
        this.type = type;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ApiErrorCode setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
