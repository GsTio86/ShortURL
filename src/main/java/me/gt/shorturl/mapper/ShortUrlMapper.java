package me.gt.shorturl.mapper;

import me.gt.shorturl.model.ShortUrlInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShortUrlMapper {

    @Insert("insert into shorturl_data (shortUrl, originalUrl, createAt, clickCount, visitCount) " +
            "values (#{shortUrl}, #{originalUrl}, #{createAt}, #{clickCount}, #{visitCount})")
    void createShortUrl(ShortUrlInfo shortUrlInfo);

    @Select("select * from shorturl_data where shortUrl = #{shortUrl}")
    ShortUrlInfo getShortUrl(String shortUrl);

    @Select("select * from shorturl_data")
    List<ShortUrlInfo> getShortUrls();

    @Update("update shorturl_data set clickCount = clickCount + 1 where shortUrl = #{shortUrl}")
    int addClickCount(String shortUrl);

    @Update("update shorturl_data set visitCount = visitCount + 1 where shortUrl = #{shortUrl}")
    int addVisitCount(String shortUrl);

    @Delete("delete from shorturl_data where shortUrl = #{shortUrl}")
    int deleteShortUrl(String shortUrl);

}
