package com.example.cseduprojecthub;

public class ProjectCard {
    String projectName;
    String projectDescroption;
    String paperLink;
    String githubLink;
    int year;
    String author;
    String topic;

    public ProjectCard() {
        ///*
        this.projectName="Project Name";
        this.projectDescroption="Project Description";
        this.paperLink="https://arxiv.org/pdf/1706.03762.pdf";
        this.githubLink="https://github.com/deathstroke44";
        this.year=2018;
        this.author="Omi";
        this.topic="ML";
        //*/

    }

    public ProjectCard(String projectName, String projectDescroption, String paperLink, String githubLink, int year, String author, String topic) {
        this.projectName = projectName;
        this.projectDescroption = projectDescroption;
        this.paperLink = paperLink;
        this.githubLink = githubLink;
        this.year = year;
        this.author = author;
        this.topic = topic;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescroption() {
        return projectDescroption;
    }

    public String getPaperLink() {
        return paperLink;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectDescroption(String projectDescroption) {
        this.projectDescroption = projectDescroption;
    }

    public void setPaperLink(String paperLink) {
        this.paperLink = paperLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }
}
