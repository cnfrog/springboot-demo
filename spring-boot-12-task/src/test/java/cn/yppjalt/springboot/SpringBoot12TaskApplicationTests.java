package cn.yppjalt.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringBoot12TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("hello world");
        message.setText("hello");
        message.setTo("157798935@qq.com");
        message.setFrom("50544216@qq.com");
        mailSender.send(message);
    }

    @Test
    public void test() throws MessagingException {
        // 1.创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        // 邮件设置
        helper.setSubject("今晚打老虎");
        helper.setText("<b style='color:red'>hello</b>");

        helper.setTo("157798935@qq.com");
        helper.setFrom("50544216@qq.com");
        // 上传文件
        helper.addAttachment("1.jpg", new File("/jeje.png"));
        mailSender.send(mimeMessage);


    }


}
