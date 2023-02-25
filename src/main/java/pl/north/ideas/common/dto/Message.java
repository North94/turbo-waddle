package pl.north.ideas.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private String title;
    private String content;

    public static Message info(String msg) {
        return new Message("info", msg);
    }
    public static Message error(String msg) {
        return new Message("Error", msg);
    }
}
