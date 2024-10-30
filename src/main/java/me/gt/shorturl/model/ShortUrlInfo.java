package me.gt.shorturl.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import me.gt.shorturl.util.DateUtils;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlInfo {

    @Schema(description = "短網址")
    private String shortUrl;

    @Schema(description = "原始網址")
    private String originalUrl;

    @Schema(description = "建立日期")
    @Builder.Default
    private String createAt = DateUtils.getCurrentTime();

    @Schema(description = "點擊次數", example = "0")
    @Builder.Default
    private int clickCount = 0;

    @Schema(description = "訪問次數", example = "0")
    @Builder.Default
    private int visitCount = 0;
}
