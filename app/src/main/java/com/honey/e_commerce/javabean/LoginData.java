package com.honey.e_commerce.javabean;

/**
 * 1.类的用途
 * 2.@authorAdministrator
 */

public class LoginData {
    /**
     * code : 200
     * datas : {"username":"bangbangtang","userid":"35","key":"e3c7d758933540afb4c0ee82ad47adc9"}
     */

    private int code;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * username : bangbangtang
         * userid : 35
         * key : e3c7d758933540afb4c0ee82ad47adc9
         */

        private String username;
        private String userid;
        private String key;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
