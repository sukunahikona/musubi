package jp.musubi.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/dashboard")
class MsbDashBoardController {
    @GetMapping("/top")
    fun show(model: Model): String {
        model.addAttribute("message", "ダッシュボード")
        return "top"
    }
}