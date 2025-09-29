package vn.iot.star.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AjaxPageController {

    // Mapping trang ajax.jsp
    @GetMapping("/ajax")
    public String ajaxPage() {
        // Trả về tên file JSP (Spring sẽ tự nối prefix + suffix)
        return "ajax"; // -> /WEB-INF/views/ajax.jsp
    }
}
