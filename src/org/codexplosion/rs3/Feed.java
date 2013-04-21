package org.codexplosion.rs3;

import java.io.Serializable;
import java.util.List;

public class Feed implements Serializable {
	private static final long serialVersionUID = 1L;
	public String title;
	public String Description;
	public String link;
	public List<Item> items;
	private boolean selected;
	
	public Feed(String title, String desc, String link, List<Item> items) {
		this.title = title;
		this.Description = desc;
		this.link = link;
		this.items = items;
	}

	public boolean selected() {
		return selected;
	}
	
	public void deselect() {
		this.selected = false;
	}
	
	public void select() {
		this.selected = true;
	}
}
