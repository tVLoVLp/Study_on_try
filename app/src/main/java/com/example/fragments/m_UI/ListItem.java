package com.example.fragments.m_UI;

public class ListItem {
    private String head;
    private String results;
    private String data;

    public ListItem(String head, String results, String data) {
        this.head = head;
        this.results = results;
        this.data = data;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHead() {
        return head;
    }

    public String getResults() {
        return results;
    }

    public String getData() {
        return data;
    }
}
