package org.example.practice001.feed;

import lombok.RequiredArgsConstructor;
import org.example.practice001.common.model.BaseResponse;
import org.example.practice001.feed.model.FeedDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/feed")
@RestController
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @GetMapping("/list")
    public ResponseEntity list(){
        List<FeedDto.ListRes> dto = feedService.list();
        return ResponseEntity.ok(BaseResponse.success(dto));
    }
}
