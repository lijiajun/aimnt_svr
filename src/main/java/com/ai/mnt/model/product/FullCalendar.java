package com.ai.mnt.model.product;

public class FullCalendar {

    // [{"id":"7850","title":"aaa","start":"2016-01-25 00:00",
    // "end":"1970-01-01 08:00","url":null,"allDay":true,"color":"#360"}
//    'id' => $row['id'],//事件id 
//    'title' => $row['title'],//事件标题 
//    'start' => date('Y-m-d H:i',$row['starttime']),//事件开始时间 
//    'end' => date('Y-m-d H:i',$row['endtime']),//结束时间 
//    'allDay' => $is_allday, //是否为全天事件 
//    'color' => $row['color'] //事件的背景色 
    
    private String id;
    private String title;
    private String start;
    private String end;
    private String url;
    private String allDay;
    private String color;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getAllDay() {
        return allDay;
    }
    public void setAllDay(String allDay) {
        this.allDay = allDay;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    
}
