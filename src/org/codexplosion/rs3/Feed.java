package org.codexplosion.rs3;

import java.io.Serializable;
import java.util.List;

public class Feed implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String Description;
	private String link;
	private List<Item> items;
	private boolean selected;

    public Feed(String title, String desc, String link, List<Item> items) {
		this.title = title;
		this.Description = desc;
		this.link = link;
		this.items = items;
	}

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }

    public String getLink() {
        return link;
    }

    public List<Item> getItems() {
        return items;
    }

	public boolean isSelected() {
		return selected;
	}
	
	public void deselect() {
		this.selected = false;
	}
	
	public void select() {
		this.selected = true;
	}

    public boolean containsUnread() {
        for(Item i : items) {
            if(!i.isRead()) {
                return true;
            }
        }
        return false;
    }

    public int getUnreadCount() {
        int unreadCount = 0;
        for(Item i : items) {
            if(!i.isRead()) {
                ++unreadCount;
            }
        }
        return unreadCount;
    }
}
