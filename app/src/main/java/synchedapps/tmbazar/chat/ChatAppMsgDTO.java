package synchedapps.tmbazar.chat;

public class ChatAppMsgDTO {

    public final static String MSG_TYPE_SENT = "0";

    public final static String MSG_TYPE_RECEIVED = "1";

    // Message content.
    private String msgContent;

    // Message type.
    private String msgType;

    private String time;

    public ChatAppMsgDTO() {

    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getTime() {     return time;    }

    public void setTime(String time) { this.time = time;    }
}