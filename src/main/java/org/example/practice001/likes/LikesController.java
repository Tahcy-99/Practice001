package org.example.practice001.likes;

import lombok.RequiredArgsConstructor;
import org.example.practice001.common.model.BaseResponse;
import org.example.practice001.user.model.AuthUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/likes")
@RestController
public class LikesController {
    private final LikesService likesService;

    @GetMapping("/{idx}")
    public ResponseEntity likes(
            @AuthenticationPrincipal AuthUserDetails user,
            @PathVariable Long idx
            ){
        likesService.like(user,idx);

        return ResponseEntity.ok(BaseResponse.success("성공"));
    }
}
