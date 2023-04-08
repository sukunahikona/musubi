package jp.musubi.controller

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/dashboard")
class MsbDashBoardController {
    @GetMapping("/top")
    fun show(model: Model): String {
        val authentication:Authentication = SecurityContextHolder.getContext().authentication
        val principal:Any? = authentication.principal
        print(principal.toString())
        model.addAttribute("message", "ダッシュボード")
        return "top"
    }
}