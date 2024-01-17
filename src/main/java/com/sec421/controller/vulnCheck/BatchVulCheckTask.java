package com.sec421.controller.vulnCheck;
/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;

public class BatchVulCheckTask extends Task<Integer> {
    private int index;
    private String vulScript;
    private String url;
    private final String cmd = null;
    private final SimpleStringProperty result = new SimpleStringProperty();

    public Integer call() throws Exception {
        String checkResult = VulCheckController.vulCheck(this.url,this.vulScript);
        setResult(checkResult);
        return null;
    }

    public BatchVulCheckTask(String url, int index) {
        this.index = index;
        this.url = url;
    }

    public BatchVulCheckTask(String url, int index, String vulScript) {
        this.vulScript = vulScript;
        this.url = url;
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult() {
        return this.result.get();
    }

    public SimpleStringProperty resultProperty() {
        return this.result;
    }

    public void setResult(String result) {
        this.result.set(result);
    }

}
