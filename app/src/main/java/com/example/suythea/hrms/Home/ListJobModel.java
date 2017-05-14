package com.example.suythea.hrms.Home;

/**
 * Created by lolzzlolzz on 6/28/16.
 */
public class ListJobModel {

    private String imgUrl;
    private String title;
    private String deadline;
    private String yearEx;
    private String cName;
    private String salary;

    public ListJobModel(String imgUrl, String title, String deadline, String yearEx, String cName, String salary) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.deadline = deadline;
        this.yearEx = yearEx;
        this.cName = cName;
        this.salary = salary;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getYearEx() {
        return yearEx;
    }

    public String getcName() {
        return cName;
    }

    public String getSalary() {
        return salary;
    }
}
