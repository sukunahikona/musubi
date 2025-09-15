package jp.musubi.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/auth")
class MsbAuthController {
    @GetMapping("/login")
    fun login(model: Model, @RequestParam("error") error: Boolean = false): String {
        model.addAttribute("errorMsg", if(error) "ログインに失敗しました" else "")
        model.addAttribute("message", "ログイン")
        return "auth/login"
    }
}