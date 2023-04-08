package jp.musubi.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/auth")
class MsbAuthController {
    @GetMapping("/login")
    fun login(model: Model): String {
        model.addAttribute("message", "ログイン")
        return "auth/login"
    }
}