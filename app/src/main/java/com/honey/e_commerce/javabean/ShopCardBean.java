package com.honey.e_commerce.javabean;

import java.util.List;

/**
 * 1. 类的用途
 * 2. @author
 */

public class ShopCardBean {

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
         * cart_count : 2
         * cart_list : [{"goods":[{"bl_id":"0","book_down_payment":"0.00","book_down_time":"0","book_final_payment":"0.00","buyer_id":"21","cart_id":"41","contractlist":[],"gc_id":"587","goods_commonid":"100006","goods_freight":"0.00","goods_id":"100006","goods_image":"1_04752627871532105.png","goods_image_url":"http://169.254.229.178/data/upload/shop/store/goods/1/1_04752627871532105_240.png","goods_name":"劳力士Rolex 蚝式恒动系列 自动机械钢带男表 正品116231-G-63201","goods_num":"5","goods_price":"100500.00","goods_storage":"100","goods_storage_alarm":"0","goods_total":"502500.00","goods_vat":"0","groupbuy_info":[],"have_gift":"0","is_book":"0","is_chain":"0","is_fcode":"0","sole_info":[],"state":true,"storage_state":true,"store_id":"1","store_name":"好商城V5","transport_id":"0","xianshi_info":[]},{"bl_id":"0","book_down_payment":"0.00","book_down_time":"0","book_final_payment":"0.00","buyer_id":"21","cart_id":"42","contractlist":[],"gc_id":"587","goods_commonid":"100005","goods_freight":"0.00","goods_id":"100005","goods_image":"1_04752627843241680.jpg","goods_image_url":"http://169.254.229.178/data/upload/shop/store/goods/1/1_04752627843241680_240.jpg","goods_name":"劳力士Rolex 蚝式恒动 115234-CA-72190自动机械钢带男表联保正品","goods_num":"7","goods_price":"65900.00","goods_storage":"100","goods_storage_alarm":"0","goods_total":"461300.00","goods_vat":"0","groupbuy_info":[],"have_gift":"0","is_book":"0","is_chain":"0","is_fcode":"0","sole_info":[],"state":true,"storage_state":true,"store_id":"1","store_name":"好商城V5","transport_id":"0","xianshi_info":[]}],"store_id":"1","store_name":"好商城V5"}]
         * sum : 963800.00
         */

        private int cart_count;
        private String sum;
        private List<CartListBean> cart_list;

        public int getCart_count() {
            return cart_count;
        }

        public void setCart_count(int cart_count) {
            this.cart_count = cart_count;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public List<CartListBean> getCart_list() {
            return cart_list;
        }

        public void setCart_list(List<CartListBean> cart_list) {
            this.cart_list = cart_list;
        }

        public static class CartListBean {
            /**
             * goods : [{"bl_id":"0","book_down_payment":"0.00","book_down_time":"0","book_final_payment":"0.00","buyer_id":"21","cart_id":"41","contractlist":[],"gc_id":"587","goods_commonid":"100006","goods_freight":"0.00","goods_id":"100006","goods_image":"1_04752627871532105.png","goods_image_url":"http://169.254.229.178/data/upload/shop/store/goods/1/1_04752627871532105_240.png","goods_name":"劳力士Rolex 蚝式恒动系列 自动机械钢带男表 正品116231-G-63201","goods_num":"5","goods_price":"100500.00","goods_storage":"100","goods_storage_alarm":"0","goods_total":"502500.00","goods_vat":"0","groupbuy_info":[],"have_gift":"0","is_book":"0","is_chain":"0","is_fcode":"0","sole_info":[],"state":true,"storage_state":true,"store_id":"1","store_name":"好商城V5","transport_id":"0","xianshi_info":[]},{"bl_id":"0","book_down_payment":"0.00","book_down_time":"0","book_final_payment":"0.00","buyer_id":"21","cart_id":"42","contractlist":[],"gc_id":"587","goods_commonid":"100005","goods_freight":"0.00","goods_id":"100005","goods_image":"1_04752627843241680.jpg","goods_image_url":"http://169.254.229.178/data/upload/shop/store/goods/1/1_04752627843241680_240.jpg","goods_name":"劳力士Rolex 蚝式恒动 115234-CA-72190自动机械钢带男表联保正品","goods_num":"7","goods_price":"65900.00","goods_storage":"100","goods_storage_alarm":"0","goods_total":"461300.00","goods_vat":"0","groupbuy_info":[],"have_gift":"0","is_book":"0","is_chain":"0","is_fcode":"0","sole_info":[],"state":true,"storage_state":true,"store_id":"1","store_name":"好商城V5","transport_id":"0","xianshi_info":[]}]
             * store_id : 1
             * store_name : 好商城V5
             */

            private String store_id;
            private String store_name;
            private List<GoodsBean> goods;

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * bl_id : 0
                 * book_down_payment : 0.00
                 * book_down_time : 0
                 * book_final_payment : 0.00
                 * buyer_id : 21
                 * cart_id : 41
                 * contractlist : []
                 * gc_id : 587
                 * goods_commonid : 100006
                 * goods_freight : 0.00
                 * goods_id : 100006
                 * goods_image : 1_04752627871532105.png
                 * goods_image_url : http://169.254.229.178/data/upload/shop/store/goods/1/1_04752627871532105_240.png
                 * goods_name : 劳力士Rolex 蚝式恒动系列 自动机械钢带男表 正品116231-G-63201
                 * goods_num : 5
                 * goods_price : 100500.00
                 * goods_storage : 100
                 * goods_storage_alarm : 0
                 * goods_total : 502500.00
                 * goods_vat : 0
                 * groupbuy_info : []
                 * have_gift : 0
                 * is_book : 0
                 * is_chain : 0
                 * is_fcode : 0
                 * sole_info : []
                 * state : true
                 * storage_state : true
                 * store_id : 1
                 * store_name : 好商城V5
                 * transport_id : 0
                 * xianshi_info : []
                 */

                private String bl_id;
                private String book_down_payment;
                private String book_down_time;
                private String book_final_payment;
                private String buyer_id;
                private String cart_id;
                private String gc_id;
                private String goods_commonid;
                private String goods_freight;
                private String goods_id;
                private String goods_image;
                private String goods_image_url;
                private String goods_name;
                private String goods_num;
                private String goods_price;
                private String goods_storage;
                private String goods_storage_alarm;
                private String goods_total;
                private String goods_vat;
                private String have_gift;
                private String is_book;
                private String is_chain;
                private String is_fcode;
                private boolean state;
                private boolean storage_state;
                private String store_id;
                private String store_name;
                private String transport_id;
                private List<?> contractlist;
                private List<?> groupbuy_info;
                private List<?> sole_info;
                private List<?> xianshi_info;

                public String getBl_id() {
                    return bl_id;
                }

                public void setBl_id(String bl_id) {
                    this.bl_id = bl_id;
                }

                public String getBook_down_payment() {
                    return book_down_payment;
                }

                public void setBook_down_payment(String book_down_payment) {
                    this.book_down_payment = book_down_payment;
                }

                public String getBook_down_time() {
                    return book_down_time;
                }

                public void setBook_down_time(String book_down_time) {
                    this.book_down_time = book_down_time;
                }

                public String getBook_final_payment() {
                    return book_final_payment;
                }

                public void setBook_final_payment(String book_final_payment) {
                    this.book_final_payment = book_final_payment;
                }

                public String getBuyer_id() {
                    return buyer_id;
                }

                public void setBuyer_id(String buyer_id) {
                    this.buyer_id = buyer_id;
                }

                public String getCart_id() {
                    return cart_id;
                }

                public void setCart_id(String cart_id) {
                    this.cart_id = cart_id;
                }

                public String getGc_id() {
                    return gc_id;
                }

                public void setGc_id(String gc_id) {
                    this.gc_id = gc_id;
                }

                public String getGoods_commonid() {
                    return goods_commonid;
                }

                public void setGoods_commonid(String goods_commonid) {
                    this.goods_commonid = goods_commonid;
                }

                public String getGoods_freight() {
                    return goods_freight;
                }

                public void setGoods_freight(String goods_freight) {
                    this.goods_freight = goods_freight;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }

                public String getGoods_image_url() {
                    return goods_image_url;
                }

                public void setGoods_image_url(String goods_image_url) {
                    this.goods_image_url = goods_image_url;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(String goods_num) {
                    this.goods_num = goods_num;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getGoods_storage() {
                    return goods_storage;
                }

                public void setGoods_storage(String goods_storage) {
                    this.goods_storage = goods_storage;
                }

                public String getGoods_storage_alarm() {
                    return goods_storage_alarm;
                }

                public void setGoods_storage_alarm(String goods_storage_alarm) {
                    this.goods_storage_alarm = goods_storage_alarm;
                }

                public String getGoods_total() {
                    return goods_total;
                }

                public void setGoods_total(String goods_total) {
                    this.goods_total = goods_total;
                }

                public String getGoods_vat() {
                    return goods_vat;
                }

                public void setGoods_vat(String goods_vat) {
                    this.goods_vat = goods_vat;
                }

                public String getHave_gift() {
                    return have_gift;
                }

                public void setHave_gift(String have_gift) {
                    this.have_gift = have_gift;
                }

                public String getIs_book() {
                    return is_book;
                }

                public void setIs_book(String is_book) {
                    this.is_book = is_book;
                }

                public String getIs_chain() {
                    return is_chain;
                }

                public void setIs_chain(String is_chain) {
                    this.is_chain = is_chain;
                }

                public String getIs_fcode() {
                    return is_fcode;
                }

                public void setIs_fcode(String is_fcode) {
                    this.is_fcode = is_fcode;
                }

                public boolean isState() {
                    return state;
                }

                public void setState(boolean state) {
                    this.state = state;
                }

                public boolean isStorage_state() {
                    return storage_state;
                }

                public void setStorage_state(boolean storage_state) {
                    this.storage_state = storage_state;
                }

                public String getStore_id() {
                    return store_id;
                }

                public void setStore_id(String store_id) {
                    this.store_id = store_id;
                }

                public String getStore_name() {
                    return store_name;
                }

                public void setStore_name(String store_name) {
                    this.store_name = store_name;
                }

                public String getTransport_id() {
                    return transport_id;
                }

                public void setTransport_id(String transport_id) {
                    this.transport_id = transport_id;
                }

                public List<?> getContractlist() {
                    return contractlist;
                }

                public void setContractlist(List<?> contractlist) {
                    this.contractlist = contractlist;
                }

                public List<?> getGroupbuy_info() {
                    return groupbuy_info;
                }

                public void setGroupbuy_info(List<?> groupbuy_info) {
                    this.groupbuy_info = groupbuy_info;
                }

                public List<?> getSole_info() {
                    return sole_info;
                }

                public void setSole_info(List<?> sole_info) {
                    this.sole_info = sole_info;
                }

                public List<?> getXianshi_info() {
                    return xianshi_info;
                }

                public void setXianshi_info(List<?> xianshi_info) {
                    this.xianshi_info = xianshi_info;
                }
            }
        }
    }
}
