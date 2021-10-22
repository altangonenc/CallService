package com.Altan.CallService.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "calls")
@Table

@ApiModel(value = "Calls model documentation",description = "Model")
public class Call {
    @Id
    @SequenceGenerator(
            name = "call_sequence",
            sequenceName = "call_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "call_sequence"
    )
    @ApiModelProperty(value = "Unique id field of call object")
    private Long id;

    @ApiModelProperty(value = "Called phone field for call object")
    private String calledPhone;

    @ApiModelProperty(value = "Caller phone field for call object")
    private String callerPhone;

    @ApiModelProperty(value = "Date field for call object")
    private LocalDateTime date;

    @ApiModelProperty(value = "--Is it seen by users?-- flag")
    private Boolean isSeen=false;

    public Call() {
    }

    public Call(Boolean isSeen) {
        this.isSeen = isSeen;
    }

    public Call(LocalDateTime date) {
        this.date = date;
    }
    public Call(Long id, String calledPhone, String callerPhone, LocalDateTime date) {
        this.id = id;
        this.calledPhone = calledPhone;
        this.callerPhone = callerPhone;
        this.date = date;
    }

    public Call(String calledPhone, String callerPhone, LocalDateTime date) {
        this.calledPhone = calledPhone;
        this.callerPhone = callerPhone;
        this.date = date;
    }

    public Call(String callerPhone, String calledPhone) {
        this.callerPhone = callerPhone;
        this.calledPhone = calledPhone;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCallerPhone() {
        return callerPhone;
    }

    public void setCallerPhone(String callerPhone) {
        this.callerPhone = callerPhone;
    }

    public String getCalledPhone() {
        return calledPhone;
    }

    public void setCalledPhone(String calledPhone) {
        this.calledPhone = calledPhone;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Call{" +
                "id=" + id +
                ", calledPhone='" + calledPhone + '\'' +
                ", callerPhone='" + callerPhone + '\'' +
                ", date=" + date +
                ", isSeen=" + isSeen +
                '}';
    }
}
