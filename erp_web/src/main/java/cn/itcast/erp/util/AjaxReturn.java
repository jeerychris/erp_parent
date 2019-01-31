/**
 *
 */
package cn.itcast.erp.util;

import com.alibaba.fastjson.JSON;

/**
 * 封闭ajax 返回json对象
 *
 * @author Administrator
 */
public class AjaxReturn {

    private boolean success;

    private String message;

    public AjaxReturn(boolean _success) {
        this.success = _success;
    }

    public AjaxReturn(String _message) {
        this.message = _message;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
