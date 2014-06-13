package com.excilys.testapp.android.model.message;

/**
 * Created by excilys on 13/06/14.
 */
public class Message {
    public String id;
    public String content;
    public String senderId;

    public static class Builder {
        Message message;

        private Builder() {
            message = new Message();
        }

        public Builder id(String id) {
            if (id != null)
                this.message.id = id;
            return this;
        }

        public Builder content(String content) {
            this.message.content = content;
            return this;
        }

        public Builder senderId(String senderId) {
            this.message.senderId = senderId;
            return this;
        }

        public Message build() {
            return this.message;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", senderId='" + senderId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!content.equals(message.content)) return false;
        if (!id.equals(message.id)) return false;
        if (!senderId.equals(message.senderId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + senderId.hashCode();
        return result;
    }
}
