package com.system.edu.models.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "support", schema = "", catalog = "journal")
public class SupportEntity {
    private int id;
    private String subject;
    private String message;
    private String email;
    private Timestamp messageDate;

    @Id
    @Column(name = "id")
    @GenericGenerator(name="subjectId" , strategy="increment")
    @GeneratedValue(generator="subjectId")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "message_date")
    public Timestamp getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Timestamp messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SupportEntity that = (SupportEntity) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (messageDate != null ? !messageDate.equals(that.messageDate) : that.messageDate != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (messageDate != null ? messageDate.hashCode() : 0);
        return result;
    }
}