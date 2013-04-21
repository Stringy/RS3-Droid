package org.codexplosion.rs3;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	public String title;
	public String content;
	public String link;
	public Date date;
	public String ID;
	public boolean read;
	
	public Item(String title, String content, String link, Date date, String id, boolean read) {
		this.title = title;
		this.content = content;
		this.link = link;
		this.date = date;
		this.ID = id;
		this.read = read;
	}
}
