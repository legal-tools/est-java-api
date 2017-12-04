package com.catalyseit;

import java.util.List;

class Agreement {
    private String name;
    private String callbackUrl;
    private List<EstRecipient> recipients;
    private Boolean autoSendMailsEnabled;

    public Agreement(String name, String callbackUrl, List<EstRecipient> recipients, Boolean autoSendMailsEnabled) {
        setName(name);
        setCallbackUrl(callbackUrl);
        setRecipients(recipients);
        setAutoSendMailsEnabled(autoSendMailsEnabled);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public List<EstRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<EstRecipient> recipients) {
        this.recipients = recipients;
    }

    public Boolean getAutoSendMailsEnabled() {
        return autoSendMailsEnabled;
    }

    public void setAutoSendMailsEnabled(Boolean autoSendMailsEnabled) {
        this.autoSendMailsEnabled = autoSendMailsEnabled;
    }
}
