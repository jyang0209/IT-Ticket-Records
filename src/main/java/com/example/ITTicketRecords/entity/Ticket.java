package com.example.ITTicketRecords.entity;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "requester")
    private String requester;
    @Column(name = "department")
    private String department;
    @Column(name = "brief_description")
    private String briefDescription;
    @Column(name = "detailed_description")
    private String detailedDescription;
    @Column(name = "resolution")
    private String resolution;
    @Column(name = "requested_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime requestedTime;
    @Column(name = "status")
    private String status;
    @Column(name = "it_member")
    private String ITmember;

    public Ticket(){

    }

    public Ticket(LocalDateTime requestedTime, String status, String requester, String department, String briefDescription, String detailedDescription, String resolution, String ITmember) {
        this.requestedTime = requestedTime;
        this.status = status;
        this.requester = requester;
        this.department = department;
        this.briefDescription = briefDescription;
        this.detailedDescription = detailedDescription;
        this.resolution = resolution;
        this.ITmember = ITmember;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(LocalDateTime requestedTime) {
        this.requestedTime = requestedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getITmember() {
        return ITmember;
    }

    public void setITmember(String ITmember) {
        this.ITmember = ITmember;
    }
}
