package com.onlyas.model;

import java.io.Serializable;

public class FileInfoVO implements Serializable {

    /**
     * trackerUrl
     */
    private String trackerUrl;

    /**
     * groupName
     */
    private String groupName;

    /**
     * remoteFileName
     */
    private String remoteFileName;

    /**
     * url
     */
    private String url;

    public String getTrackerUrl() {
        return trackerUrl;
    }

    public void setTrackerUrl(String trackerUrl) {
        this.trackerUrl = trackerUrl;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRemoteFileName() {
        return remoteFileName;
    }

    public void setRemoteFileName(String remoteFileName) {
        this.remoteFileName = remoteFileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
