package com.example.cseduprojecthub;

public class User {
    String Contact,Mail,Name,Picture,Research,Session,pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public User(String contact, String mail, String name, String picture, String research, String session) {
        Contact = contact;
        Mail = mail;
        Name = name;
        Picture = picture;
        Research = research;
        Session = session;
    }

    public User(String contact, String mail, String name, String picture, String research, String session, String pic) {
        Contact = contact;
        Mail = mail;
        Name = name;
        Picture = picture;
        Research = research;
        Session = session;
        this.pic = pic;
    }

    public User() {
        Contact=Mail=Name=Picture=Research=Session=pic="N/A";
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getResearch() {
        return Research;
    }

    public void setResearch(String research) {
        Research = research;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }
}
