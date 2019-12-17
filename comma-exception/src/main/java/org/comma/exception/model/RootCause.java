package org.comma.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.MoreObjects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "reason",
        "debug_message"
})
public class RootCause {

    @JsonProperty("type")
    private String type;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("debug_message")
    private String debugMessage;

    /**
     * No args constructor for use in serialization
     */
    public RootCause() {
    }

    /**
     * @param reason
     * @param debugMessage
     * @param type
     */
    public RootCause(String type, String reason, String debugMessage) {
        super();
        this.type = type;
        this.reason = reason;
        this.debugMessage = debugMessage;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public RootCause withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public RootCause setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public RootCause withReason(String reason) {
        this.reason = reason;
        return this;
    }

    @JsonProperty("debug_message")
    public String getDebugMessage() {
        return debugMessage;
    }

    @JsonProperty("debug_message")
    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public RootCause withDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("reason", reason)
                .add("debugMessage", debugMessage)
                .toString();
    }
}