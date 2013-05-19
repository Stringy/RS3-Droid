package org.codexplosion.rs3;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String content;
	private String link;
	private Date date;
	private String ID;
	private boolean read;
	
	public Item(String title, String content, String link, Date date, String id, boolean read) {
		this.title = title;
		this.content = content;
		this.link = link;
		this.date = date;
		this.ID = id;
		this.read = read;
	}

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLink(){
        return link;
    }

    public Date getDate() {
        return date;
    }

    public String getID() {
        return ID;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean val) {
        this.read = val;
    }
}
