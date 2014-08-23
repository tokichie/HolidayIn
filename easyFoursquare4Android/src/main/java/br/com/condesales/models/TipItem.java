package br.com.condesales.models;

public class TipItem {

	private String id;

	private long createdAt;

	private String canonicalUrl;

    private Likes likes;

    private boolean like;
    private boolean logView;

    private String type;
    private String text;

    private User user;

	public String getCanonicalUrl() { return canonicalUrl; }

    public Likes getLikes() { return likes; }

	public String getId() {
		return id;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public boolean getLike() { return like; }

    public boolean getLogView() { return logView; }

    public String getType() { return type; }

    public String getText() { return text; }

    public User getUser() { return user; }

}
