package com.example.vidit.collnect;

/**
 * Created by vidit on 01-12-2015.
 */
public class FacultyDetail {
    private String name;
    private String department;
    private String urlThumbnail;
    private String rank;
    private String prof_uri;

    public FacultyDetail(){}

    public FacultyDetail(String name,String department,String urlThumbnail,String rank){
        this.name = name;
        this.department = department;
        this.urlThumbnail = urlThumbnail;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "FacultyDetail{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", urlThumbnail='" + urlThumbnail + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getProf_uri() {
        return prof_uri;
    }

    public void setProf_uri(String prof_uri) {
        this.prof_uri = prof_uri;
    }
}
