package com.imzhiqiang.imageloaders.entity;

import java.util.List;

/**
 * Created by Zech on 2016/1/1.
 */
public class MeizhiEntity extends BaseEntity{

    /**
     * who : 张涵宇
     * publishedAt : 2015-12-31T04:29:30.902Z
     * desc : 12.31--2015最后一天福利满满
     * type : 福利
     * url : http://ww2.sinaimg.cn/large/7a8aed7bjw1ezil3n0cqdj20p00ou776.jpg
     * used : true
     * objectId : 5684856800b009a31af4d3bf
     * createdAt : 2015-12-31T01:31:20.602Z
     * updatedAt : 2015-12-31T04:29:31.714Z
     */

    private List<ResultsEntity> results;

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class ResultsEntity {
        private String who;
        private String publishedAt;
        private String desc;
        private String type;
        private String url;
        private boolean used;
        private String objectId;
        private String createdAt;
        private String updatedAt;

        public void setWho(String who) {
            this.who = who;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getWho() {
            return who;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getDesc() {
            return desc;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        public boolean isUsed() {
            return used;
        }

        public String getObjectId() {
            return objectId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }
    }
}
