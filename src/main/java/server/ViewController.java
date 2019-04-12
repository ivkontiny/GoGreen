package server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    /**
     * Sets a new password.
     * @param recoverId the id of the recovered account
     * @return a html page
     */
    @GetMapping("/recover/{recoverId}")
    public ModelAndView setNewPassword(@PathVariable("recoverId") String recoverId) {
        ModelAndView mv = new ModelAndView();
        if (ResetPasswordController.recoverRequests.containsKey(recoverId)) {
            mv.setViewName("gogreen.html");
        }
        return mv;

    }
}
