package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import umc.study.domain.enums.Gender;
import umc.study.service.UserService.command.UserCommandService;
import umc.study.web.dto.userDTO.UserReqDTO;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserViewController {

    private final UserCommandService userCommandService;

    @ModelAttribute("genders")
    public Gender[] genders() {
        return Gender.values();
    }

    @PostMapping("/users/signup")
    public String joinUser(@ModelAttribute("createUserReqDTO") UserReqDTO.CreateUserReqDTO createUserReqDTO, // 협업시에는 기존 RequestBody 어노테이션을 붙여주시면 됩니다!
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            // 뷰에 데이터 바인딩이 실패할 경우 signup 페이지를 유지합니다.
            return "signup";
        }

        try {
            log.info("joinUser하기 직전입니다. 현재 DTO 속 password 값: {}", createUserReqDTO.getPassword());
            userCommandService.joinUser(createUserReqDTO);
            return "redirect:/login";
        } catch (Exception e) {
            // 회원가입 과정에서 에러가 발생할 경우 에러 메시지를 보내고, signup 페이디를 유지합니다.
            log.info("에러났어요 무슨 에러냐면 : {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("createUserReqDTO", new UserReqDTO.CreateUserReqDTO());
        return "signup";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}