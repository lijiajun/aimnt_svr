package com.ai.mnt.model.stat;

import java.util.ArrayList;
import java.util.List;

public class EmailInfo {

    private String host;
    private String protocol;
    private boolean isSmtpAuth = true;
    private String username;
    private String password;
    private String fromAddr;
    private List<String> toAddrs = new ArrayList<>();
    private List<String> CCs = new ArrayList<>();
    private String subject;
    private String content;
    private String subType;
    private List<EmailAttachment> emailAttachs = new ArrayList<>();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public boolean isSmtpAuth() {
        return isSmtpAuth;
    }

    public void setSmtpAuth(boolean isSmtpAuth) {
        this.isSmtpAuth = isSmtpAuth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFromAddr() {
        return fromAddr;
    }

    public void setFromAddr(String fromAddr) {
        this.fromAddr = fromAddr;
    }

    public List<String> getToAddrs() {
        return toAddrs;
    }

    public void setToAddrs(List<String> toAddrs) {
        this.toAddrs = toAddrs;
    }

    public List<String> getCCs() {
        return CCs;
    }

    public void setCCs(List<String> cCs) {
        CCs = cCs;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<EmailAttachment> getEmailAttachs() {
        return emailAttachs;
    }

    public void setEmailAttachs(List<EmailAttachment> emailAttachs) {
        this.emailAttachs = emailAttachs;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    @Override
    public String toString() {
        return "EmailInfo [host=" + host + ", protocol=" + protocol
                + ", isSmtpAuth=" + isSmtpAuth + ", username=" + username
                + ", password=" + password + ", fromAddr=" + fromAddr
                + ", toAddrs=" + toAddrs + ", CCs=" + CCs + ", subject="
                + subject + ", subType=" + subType + ", emailAttachs="
                + emailAttachs + ", content=" + content + "]";
    }

}
