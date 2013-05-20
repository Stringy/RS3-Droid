package org.codexplosion.rs3.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giles on 20/05/13.
 */
public class User {
    private List<Feed> feeds = new ArrayList<Feed>();

    public User() {
        List<Item> items = new ArrayList<Item>();

        items.add(new Item("MPs challenge Google over UK tax",
                "MPs challenge Google over reporting of income for UK tax, " +
                        "but the internet giant says there are no transactions executed in Britain.",
                "http://www.bbc.co.uk/news/business-22551401#sa-ns_mchannel=rss&ns_source=PublicRSS20-sa",
                null, "", false));

        items.add(new Item("MPs challenge Google over UK tax",
                "MPs challenge Google over reporting of income for UK tax, " +
                        "but the internet giant says there are no transactions executed in Britain.",
                "http://www.bbc.co.uk/news/business-22551401#sa-ns_mchannel=rss&ns_source=PublicRSS20-sa",
                null, "", false));

        items.add(new Item("MPs challenge Google over UK tax",
                "MPs challenge Google over reporting of income for UK tax, " +
                        "but the internet giant says there are no transactions executed in Britain.",
                "http://www.bbc.co.uk/news/business-22551401#sa-ns_mchannel=rss&ns_source=PublicRSS20-sa",
                null, "", false));

        items.add(new Item("MPs challenge Google over UK tax",
                "MPs challenge Google over reporting of income for UK tax, " +
                        "but the internet giant says there are no transactions executed in Britain.",
                "http://www.bbc.co.uk/news/business-22551401#sa-ns_mchannel=rss&ns_source=PublicRSS20-sa",
                null, "", false));

        items.add(new Item("MPs challenge Google over UK tax",
                "MPs challenge Google over reporting of income for UK tax, " +
                        "but the internet giant says there are no transactions executed in Britain.",
                "http://www.bbc.co.uk/news/business-22551401#sa-ns_mchannel=rss&ns_source=PublicRSS20-sa",
                null, "", false));

        feeds.add(new Feed(
                "BBC News",
                "News from around the world",
                "http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/uk/rss.xml",
                items));
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds.clear();
        this.feeds.addAll(feeds);
    }

    public List<Feed> getFeeds() {
        return feeds;
    }
}
