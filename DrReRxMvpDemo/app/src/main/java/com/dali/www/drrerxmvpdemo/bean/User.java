package com.dali.www.drrerxmvpdemo.bean;

import com.dali.www.drrerxmvpdemo.bean.base.BaseBean;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qinkangli on 2016/12/2.
 */
public class User extends BaseBean{
    /**
     * code : 1
     * data : [{"id":"4","type":"ESD防护中级课程","icon":null,"num":3},{"id":"7","type":"ESD防护工程师课程","icon":null,"num":2}]
     */

    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 4
         * type : ESD防护中级课程
         * icon : null
         * num : 3
         */

        private String id;
        private String type;
        private Object icon;
        private int num;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }



}
