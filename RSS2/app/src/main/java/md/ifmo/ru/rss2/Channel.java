package md.ifmo.ru.rss2;

/**
 * Created by Илья on 16.01.2015.
 */
public class Channel {
    public final String name;
    public final String url;
    public final long id;
    public Channel(String name, String url, long id){
        this.name = name;
        this.url = url;
        this.id = id;
    }
}
