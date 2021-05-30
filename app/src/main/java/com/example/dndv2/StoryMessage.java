package com.example.dndv2;

public class StoryMessage
{
    private String speaker;
    private String message;

    StoryMessage(String speaker, String message)
    {
        this.speaker = speaker;
        this.message = message;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getMessage() {
        return message;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString()
    {
        return speaker + ": " + message;
    }
}
