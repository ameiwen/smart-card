package com.zx.card.system.controller;

import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.*;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.zx.card.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.Random;

@Controller
public class VerCodeController {

    /**
     * 图片验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/captcha")
    public void captchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Random random = new Random();
        ConfigurableCaptchaService cs = genCodeInit(random);
        switch (random.nextInt(5)) {
            case 0:
                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
                break;
            case 1:
                cs.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 2:
                cs.setFilterFactory(new DoubleRippleFilterFactory());
                break;
            case 3:
                cs.setFilterFactory(new WobbleRippleFilterFactory());
                break;
            case 4:
                cs.setFilterFactory(new DiffuseRippleFilterFactory());
                break;
        }
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
        HttpSession session = request.getSession(true);
        String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
        session.setAttribute(Constants.PIC_CAPTCHA, token);
    }

    private ConfigurableCaptchaService genCodeInit(Random random) {
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new ColorFactory() {
            public Color getColor(int x) {
                int[] c = {25, 60, 170};
                int i = random.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = random.nextInt(71);
                    } else {
                        c[fi] = random.nextInt(256);
                    }
                }
                return new Color(c[0], c[1], c[2]);
            }
        });
        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters("0123456789");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setWordFactory(wf);
        return cs;
    }
}
