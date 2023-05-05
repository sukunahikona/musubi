package jp.musubi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class MsbHealthCheckController {

    @GetMapping("/check")
    fun health ():String {
        return "health"
    }
}