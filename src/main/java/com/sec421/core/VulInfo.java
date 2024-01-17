package com.sec421.core;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author 0x421
 * @date 2023/12/28 11:20
 * @github https://github.com/0x7eTeam
 */

public class VulInfo {
    private final SimpleStringProperty id = new SimpleStringProperty();
    private final SimpleStringProperty target = new SimpleStringProperty();
    private final SimpleStringProperty isVul = new SimpleStringProperty();

    public VulInfo(String id, String target, String isVul) {
        setId(id);
        setTarget(target);
        setIsVul(isVul);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getTarget() {
        return target.get();
    }

    public void setTarget(String target) {
        this.target.set(target);
    }

    public String getIsVul() {
        return isVul.get();
    }

    public void setIsVul(String isVul) {
        this.isVul.set(isVul);
    }

    @Override
    public String toString() {
        return "VulInfo{" +
                "id=" + id +
                ", target=" + target +
                ", isVul=" + isVul +
                '}';
    }
}
