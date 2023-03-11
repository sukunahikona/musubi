package jp.musubi.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller("/")
class MsbTopController {
    @GetMapping("/top")
    fun show(model: Model): String {
        model.addAttribute("message", "Hello World!!!")
        return "top"
    }
}