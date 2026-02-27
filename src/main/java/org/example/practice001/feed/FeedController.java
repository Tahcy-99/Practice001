package org.example.practice001.feed;

import lombok.RequiredArgsConstructor;
import org.example.practice001.common.model.BaseResponse;
import org.example.practice001.feed.model.FeedDto;
import org.example.practice001.user.model.AuthUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping("/reg")
    public ResponseEntity reg(@RequestBody FeedDto.FeedReg dto, @AuthenticationPrincipal AuthUserDetails user){
        feedService.reg(dto, user);
        return ResponseEntity.ok(BaseResponse.success("등록 성공"));
    }
}
