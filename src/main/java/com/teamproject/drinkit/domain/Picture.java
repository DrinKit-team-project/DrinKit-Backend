package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Picture {
    @JsonProperty(value = "data")
    private Data data;

    public static class Data {
        private int height;
        private boolean isSilhouette;
        private int width;
        private String url;

        @JsonCreator
        public Data(@JsonProperty(value = "id") int height, @JsonProperty(value = "is_silhouette") boolean isSilhouette,
                    @JsonProperty(value = "width") int width, @JsonProperty(value = "url") String url){
            this.height = height;
            this.isSilhouette = isSilhouette;
            this.width = width;
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "height=" + height +
                    ", isSilhouette=" + isSilhouette +
                    ", width=" + width +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public Data getData() {
        return data;
    }
}
