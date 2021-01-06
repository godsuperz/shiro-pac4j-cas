package com.superz.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Resource
    private MailService mailService;
    @Resource
    private TemplateEngine templateEngine;

    @Test
    public void sayHelloTest() {
        mailService.sayHello();
    }

    @Test
    public void sendSimpleMailTest() {
        mailService.sendSimpleMail("lovezhangchao@outlook.com", "superz", "I'm SuperZ!!");
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        StringBuilder content = new StringBuilder();
        content.append("<html><head>\n" +
                "<base target=\"_blank\">\n" +
                "<style type=\"text/css\">\n" +
                "::-webkit-scrollbar{ display: none; }\n" +
                "</style>\n" +
                "<style id=\"cloudAttachStyle\" type=\"text/css\">\n" +
                "#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}\n" +
                "</style>\n" +
                "\n" +
                "</head>\n" +
                "<body tabindex=\"0\" role=\"listitem\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t\n" +
                "\n" +
                "\t<style type=\"text/css\">\n" +
                "\t\tbody{ margin:0 auto; padding:0; font-family:Microsoft Yahei,Tahoma,Arial; color:#333333;background-color:#fff; font-size:12px;}\n" +
                "\t\ta{color:#00a2ca; line-height:22px; text-decoration:none;}\n" +
                "\t\ta:hover{text-decoration:underline;color:#00a2ca;}\n" +
                "\t\ttd{font-family:'Microsoft YaHei';}\n" +
                "\t</style>\n" +
                "\n" +
                "\t\n" +
                "\t\t\n" +
                "\n" +
                "\n" +
                "<center><table align=\"center\" width=\"800\" style=\"font-family:'Microsoft YaHei';margin:10px 0 10px 0;background-color:#fff;color:#333333;border:1px solid #edecec;\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td>\n" +
                "\n" +
                "\n" +
                "          <div>\n" +
                "                <table width=\"800\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#373d41\" height=\"48\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td width=\"74\" height=\"48\" border=\"0\" align=\"center\" valign=\"middle\" style=\"padding-left:20px;height:48px;\">\n" +
                "                        <a href=\"http://www.aliyun.com/?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnt.14&amp;v=q\" target=\"_blank\">\n" +
                "                          <img src=\"https://img.alicdn.com/tfs/TB1ECS5M3HqK1RjSZFkXXX.WFXa-80-48.png\" alt=\"阿里云\" width=\"80\" height=\"48\" border=\"0\">\n" +
                "                      </a>\n" +
                "                  </td>\n" +
                "                      <td width=\"703\" height=\"48\" colspan=\"2\" align=\"right\" valign=\"middle\" style=\"color:#ffffff; padding-right:20px;height:48px;\">                  \n" +
                "                          <a href=\"http://www.aliyun.com/?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnt.15&amp;\" target=\"_blank\" style=\"color:#ffffff;text-decoration:none;font-family:'Microsoft YaHei';\">首页</a>\n" +
                "                          &nbsp;&nbsp;&nbsp;<span style=\"color:#6c7479;\">|</span>&nbsp;&nbsp;  \n" +
                "                          <a href=\"http://www.aliyun.com/product/ecs/?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnt.16&amp;\" target=\"_blank\" style=\"color:#ffffff;text-decoration:none;font-family:'Microsoft YaHei';\">产品服务</a>\n" +
                "                          &nbsp;&nbsp;&nbsp;<span style=\"color:#6c7479;\">|</span>&nbsp;&nbsp;  \n" +
                "                          <a href=\"http://www.aliyun.com/act/webbaindex.html?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnt.17&amp;\" target=\"_blank\" style=\"color:#ffffff;text-decoration:none;font-family:'Microsoft YaHei';\">备案专区</a>\n" +
                "                          &nbsp;&nbsp;&nbsp;<span style=\"color:#6c7479;\">|</span>&nbsp;&nbsp; \n" +
                "                          <a href=\"http://home.console.aliyun.com/?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnt.18&amp;\" target=\"_blank\" style=\"color:#ffffff;text-decoration:none;font-family:'Microsoft YaHei';\">管理控制台</a>\n" +
                "                          &nbsp;&nbsp;&nbsp;<span style=\"color:#6c7479;\">|</span>&nbsp;&nbsp;\n" +
                "                          <a href=\"http://i.aliyun.com/?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;spm=5176.383338.201.51.1UNnoL\" target=\"_blank\" style=\"color:#ffffff;text-decoration:none;\">用户中心</a>\n" +
                "                          &nbsp;&nbsp;&nbsp;<span style=\"color:#6c7479;\">|</span>&nbsp;&nbsp; \n" +
                "                          <a href=\"http://help.aliyun.com/?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnt.19&amp;\" target=\"_blank\" style=\"color:#ffffff;text-decoration:none;font-family:'Microsoft YaHei';\">帮助中心</a>                 \n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                </tbody></table>\n" +
                "          </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "          <div style=\"background:#FFF;padding:20px;text-align:left;\">\n" +
                "              \n" +
                "\n" +
                "\n" +
                "\n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "  <style type=\"text/css\">\n" +
                "    body {\n" +
                "      width: 800px;\n" +
                "      margin: 0 auto;\n" +
                "      padding: 0;\n" +
                "      font-family: \"Microsoft Yahei\";\n" +
                "      background-color: #fff;\n" +
                "      padding-bottom: 20px;\n" +
                "    }\n" +
                "\n" +
                "    .class-title {\n" +
                "      font-size: 24px;\n" +
                "      font-weight: bold;\n" +
                "      color: #373d41;\n" +
                "      width: 150px;\n" +
                "      border-bottom: 5px solid #20b2ff;\n" +
                "    }\n" +
                "\n" +
                "    .class-desc {\n" +
                "      vertical-align: bottom;\n" +
                "      font-size: 18px;\n" +
                "      color: #73777a;\n" +
                "      padding-left: 20px;\n" +
                "    }\n" +
                "\n" +
                "    .r-title {\n" +
                "      font-size: 24px;\n" +
                "      font-weight: bold;\n" +
                "      color: #373d41;\n" +
                "      width: 100px;\n" +
                "      border-bottom: 5px solid #20b2ff;\n" +
                "    }\n" +
                "\n" +
                "    .r-desc {\n" +
                "      vertical-align: bottom;\n" +
                "      font-size: 18px;\n" +
                "      color: #73777a;\n" +
                "      padding-left: 20px;\n" +
                "    }\n" +
                "  </style>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table width=\"800\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-bottom: 30px;\">\n" +
                "    <tbody><tr width=\"800\" align=\"center\" border=\"0\">\n" +
                "      <td width=\"800\" align=\"center\" height=\"260\" border=\"0\" style=\"font-size:0\">\n" +
                "        <img src=\"https://img.alicdn.com/tfs/TB1p5sTHyLaK1RjSZFxXXamPFXa-800-260.jpg\" alt=\"\">\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table style=\"margin: 0 auto; border-bottom: 1px solid #c1c1c1;\" width=\"784\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "    <tbody><tr class=\"c-title\">\n" +
                "      <td class=\"class-title\" align=\"center\">热门免费课程</td>\n" +
                "      <td class=\"class-desc\">一键Get人工智能、云计算热门技术</td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "  <table style=\"margin-top: 20px; padding: 20px; margin-bottom: 20px;\" width=\"800\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "    <tbody><tr class=\"hotclassone\" style=\"width:760px; height: 170px; box-shadow: 0 0 5px 5px #e4e4e4; padding-top:20px; padding-bottom: 20px; display: block;\">\n" +
                "      <td class=\"classoneimg\" style=\"width: 290px;height: 170px; padding-left: 24px;\">\n" +
                "        <img src=\"https://img.alicdn.com/tfs/TB1ao0bHSrqK1RjSZK9XXXyypXa-290-170.jpg\" alt=\"\">\n" +
                "      </td>\n" +
                "      <td style=\"width:476px \">\n" +
                "        <table style=\"padding-left: 24px; height: 170px; padding-right: 24px;\">\n" +
                "          <tbody><tr style=\"vertical-align: top; height: 43px;\">\n" +
                "            <td style=\"font-size: 18px; font-weight: bold; color: #373d41;\">云计算的前世今生</td>\n" +
                "          </tr>\n" +
                "          <tr style=\"font-size:14px; color:#73777a; vertical-align: top;\">\n" +
                "            <td style=\"font-size:14px; color:#73777a;\">你的日常生活中，早已离不开云计算技术。那么，如何定义云计算，云计算到底为我们带来了什么？也许你可以在这门课程中找到答案。</td>\n" +
                "          </tr>\n" +
                "          <tr style=\"text-align:right; vertical-align:bottom;\">\n" +
                "            <td align=\"right\">\n" +
                "              <a href=\"https://edu.aliyun.com/course/1236?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnc.1&amp;\" target=\"_blank\" style=\"text-decoration: none; color:#00c1de; font-size: 14px; display: block; width: 70px; height: 20px;\">立即查看&gt;</a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr class=\"hotclassone\" style=\"width:760px; height: 170px; box-shadow: 0 0 5px 5px #e4e4e4; padding-top:20px; margin-top: 20px; padding-bottom: 20px; display: block;\">\n" +
                "      <td class=\"classoneimg\" style=\"width: 290px;height: 170px; padding-left: 24px;\">\n" +
                "        <img src=\"https://img.alicdn.com/tfs/TB1.04lHFzqK1RjSZSgXXcpAVXa-290-170.jpg\" alt=\"\">\n" +
                "      </td>\n" +
                "      <td style=\"width:476px \">\n" +
                "        <table style=\"padding-left: 24px; height: 170px; padding-right: 24px;\">\n" +
                "          <tbody><tr style=\"vertical-align: top; height: 43px;\">\n" +
                "            <td style=\"font-size: 18px; font-weight: bold; color: #373d41;\">人工智能技术精品课集锦</td>\n" +
                "          </tr>\n" +
                "          <tr style=\"font-size:14px; color:#73777a; vertical-align: top;\">\n" +
                "            <td style=\"font-size:14px; color:#73777a;\">人工智能核心技术及应用课程集锦放送！从基础算法到实战应用，循序渐进带你领略技术魅力。</td>\n" +
                "          </tr>\n" +
                "          <tr style=\"text-align:right; vertical-align:bottom;\">\n" +
                "            <td>\n" +
                "              <a href=\"https://edu.aliyun.com/promotion/188?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnc.2&amp;\" target=\"_blank\" style=\"text-decoration: none; color:#00c1de; font-size: 14px;\">立即查看&gt;</a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr align=\"center\" style=\"margin-top: 25px; display: block; \">\n" +
                "      <td style=\"background: url(https://img.alicdn.com/tfs/TB1GCzpHPDpK1RjSZFrXXa78VXa-238-40.png); width: 238px; height: 40px;\">\n" +
                "        <a href=\"https://edu.aliyun.com/developer?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnc.3&amp;\" target=\"_blank\" style=\"font-size: 16px; color:#fff; text-decoration: none; width:238px;height: 40px; \n" +
                "          display: block; line-height: 40px;\">\n" +
                "          查看更多免费课程&gt;\n" +
                "        </a>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "  <table style=\"margin: 0 auto; border-bottom: 1px solid #c1c1c1;\" width=\"784\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> \n" +
                "    <tbody><tr class=\"c-title\">\n" +
                "      <td class=\"r-title\" align=\"center\">热门认证</td> \n" +
                "      <td class=\"r-desc\">定义云生态技术标准，成长为行业领先人才</td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "  <table style=\"margin: 0 auto; margin-top: 20px; padding: 20px; width: 800px;\">\n" +
                "    <tbody><tr style=\"padding-bottom: 10px;\">\n" +
                "      <td style=\"width: 240px; box-shadow: 0 0 3px 3px #e4e4e4; height: 290px; padding-bottom: 30px; display: inline-block;\">\n" +
                "        <table style=\"width: 240px; height: 290px; margin: 0;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "          <tbody><tr>\n" +
                "            <td style=\"display: block; padding: 0;\">\n" +
                "              <img src=\"https://img.alicdn.com/tfs/TB1Z90LHH2pK1RjSZFsXXaNlXXa-240-135.jpg\" alt=\"\">\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr style=\"font-size: 16px; font-weight: bold; padding-left: 25px; padding-right: 25px; display: block;\">\n" +
                "            <td style=\"font-size: 16px; font-weight: bold;\">Clouder认证：网站建设-部署与发布</td>\n" +
                "          </tr>\n" +
                "          <tr style=\"font-size: 14px; color:#73777a; padding-left: 25px; padding-right: 25px; display: block; margin-top: 10px; margin-bottom: 15px;\">\n" +
                "            <td style=\"font-size: 14px; color:#73777a;\">掌握如何将设计完成的本地静态网站发布到Internet，并让全世界的网民访问。</td>\n" +
                "          </tr>\n" +
                "          <tr style=\" padding-left: 25px; padding-right: 25px; display: block;\" align=\"right\">\n" +
                "            <td>\n" +
                "              <a href=\"https://edu.aliyun.com/certification/CLDACP01?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnc.4&amp;\" target=\"_blank\" style=\"font-size: 14px; color:#00c1de; text-decoration: none;\">立即查看&gt;</a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "      </td>\n" +
                "\n" +
                "      <td style=\"width: 240px; box-shadow: 0 0 3px 3px #e4e4e4; height: 290px; padding-bottom: 30px; display: inline-block; margin-left: 15px;\">\n" +
                "        <table style=\"width: 240px; height: 290px;margin: 0; \" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "          <tbody><tr>\n" +
                "            <td style=\"display: block; padding: 0;\">\n" +
                "              <img src=\"https://img.alicdn.com/tfs/TB1qS4WHIfpK1RjSZFOXXa6nFXa-240-135.jpg\" alt=\"\">\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr style=\"font-size: 16px; font-weight: bold; padding-left: 25px; padding-right: 25px; display: block;\">\n" +
                "            <td style=\"font-size: 16px; font-weight: bold;\">阿里云云计算工程师认证（ACP级别）</td>\n" +
                "          </tr>\n" +
                "          <tr style=\"font-size: 14px; color:#73777a; padding-left: 25px; padding-right: 25px; display: block; margin-top: 10px; margin-bottom: 15px;\">\n" +
                "            <td style=\"font-size: 14px; color:#73777a;\">阿里云最受欢迎的专业技术认证，面向使用阿里云云计算产品的架构、开发、运维人员。</td>\n" +
                "          </tr>\n" +
                "          <tr style=\" padding-left: 25px; padding-right: 25px; display: block;\" align=\"right\">\n" +
                "            <td>\n" +
                "              <a href=\"https://edu.aliyun.com/certification/acp01?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnc.5&amp;\" target=\"_blank\" style=\"font-size: 14px; color:#00c1de; text-decoration: none;\">立即查看&gt;</a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "      </td>\n" +
                "\n" +
                "      <td style=\"width: 240px; box-shadow: 0 0 3px 3px #e4e4e4; height: 290px; padding-bottom: 30px; display: inline-block; margin-left: 15px;\">\n" +
                "        <table style=\"width: 240px; height: 290px; margin: 0;\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "          <tbody><tr>\n" +
                "            <td style=\"display: block; padding: 0;\">\n" +
                "              <img src=\"https://img.alicdn.com/tfs/TB1zv0IHSzqK1RjSZPxXXc4tVXa-240-135.jpg\" alt=\"\">\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr style=\"font-size: 16px; font-weight: bold; padding-left: 25px; padding-right: 25px; display: block;\">\n" +
                "            <td style=\"font-size: 16px; font-weight: bold;\">Clouder认证：Python数据科学分析实战</td>\n" +
                "          </tr>\n" +
                "          <tr style=\"font-size: 14px; color:#73777a; padding-left: 25px; padding-right: 25px; display: block; margin-top: 10px; margin-bottom: 15px;\">\n" +
                "            <td style=\"font-size: 14px; color:#73777a;\">人工智能时代，Python数据科学是从事AI所必须的技能。场景化实战Get数据分析方法。</td>\n" +
                "          </tr>\n" +
                "          <tr style=\" padding-left: 25px; padding-right: 25px; display: block;\" align=\"right\">\n" +
                "            <td>\n" +
                "              <a href=\"https://edu.aliyun.com/certification/clda02?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnc.6&amp;\" target=\"_blank\" style=\"font-size: 14px; color:#00c1de; text-decoration: none;\">立即查看&gt;</a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table width=\"800\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "    <tbody><tr align=\"center\" style=\"margin-top: 5px; display: block; \">\n" +
                "      <td style=\"background: url(https://img.alicdn.com/tfs/TB1GCzpHPDpK1RjSZFrXXa78VXa-238-40.png); width: 238px; height: 40px;\">\n" +
                "        <a href=\"https://edu.aliyun.com/certification?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;&amp;spm=a2c4k.12571850.zh-cnc.7&amp;\" target=\"_blank\" style=\"font-size: 16px; color:#fff; text-decoration: none; width:238px;height: 40px; \n" +
                "              display: block; line-height: 40px;\">\n" +
                "          阿里云认证全景图 &gt;\n" +
                "        </a>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "<img src=\"http://ac.mmstat.com/aliyun.12.1?logtype=4&amp;type=email&amp;msgid=7690119042000149666&amp;areaid=cn&amp;siteId=cn\">\n" +
                "\n" +
                "\n" +
                "          </div>\n" +
                "\n" +
                "\n" +
                "          <table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:14px;color:#333333;\">\n" +
                "                    <tbody><tr>\n" +
                "                      <td height=\"14\" style=\"padding:0 0 16px 20px; border-bottom:1px dashed #e5e5e5;font-family:'Microsoft YaHei';\">阿里云计算有限公司</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                      <td height=\"14\" style=\"padding:8px 0 28px 20px;color:#999999; font-size:12px;font-family:'Microsoft YaHei';\">此为系统邮件请勿回复</td>\n" +
                "                    </tr>\n" +
                "                </tbody></table>\n" +
                "\n" +
                "\n" +
                "          <table width=\"100%\" height=\"100\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                  <tbody><tr>\n" +
                "                      <td width=\"800\" height=\"100\" align=\"center\" valign=\"middle\">\n" +
                "                        <img border=\"0\" height=\"100\" src=\"https://img.alicdn.com/tfs/TB14EaIfTZmx1VjSZFGXXax2XXa-800-100.png\">\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "        <table align=\"center\" border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#fff\">\n" +
                "            <tbody><tr>\n" +
                "               <td>\n" +
                "                <p style=\"line-height: 22px; font-family: 'Microsoft YaHei'; font-size: 12px; color: #999; text-align: center;\">\n" +
                "                    如果您不想再接收阿里云的「产品教育内容」邮件，或者需要设置其他人接收，点此<a href=\"https://account.aliyun.com/login/login.htm?&amp;msctype=email&amp;mscareaid=cn&amp;mscsiteid=cn&amp;mscmsgid=7690119042000149666&amp;oauth_callback=https%3a%2f%2fnotifications.console.aliyun.com%2f%23%2fsubscribeMsg%3fmessageTypeId%3dprod_edu_content\" target=\"_blank\" style=\"text-decoration:none; color:#00a2ca;\">设置订阅。</a>\n" +
                "                 </p>\n" +
                "               </td>\n" +
                "            </tr>\n" +
                "        </tbody></table>\n" +
                "\n" +
                "\t\t \n" +
                "\n" +
                "\n" +
                "</td></tr></tbody></table></center>\n" +
                "\n" +
                "\t<img src=\"http://ac.mmstat.com/aliyun.12.1?logtype=4&amp;type=email&amp;msgid=7690119042000149666&amp;areaid=cn&amp;siteId=cn\">\n" +
                " \n" +
                "\n" +
                "<style type=\"text/css\">\n" +
                "body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}\n" +
                "td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}\n" +
                "pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}\n" +
                "th,td{font-family:arial,verdana,sans-serif;line-height:1.666}\n" +
                "img{ border:0}\n" +
                "header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}\n" +
                "blockquote{margin-right:0px}\n" +
                "</style>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<style id=\"ntes_link_color\" type=\"text/css\">a,td a{color:#064977}</style>\n" +
                "\n" +
                "</body></html>");
        mailService.sendHtmlMail("lovesuperzc@163.com", "SuperZ", content.toString());
    }

    @Test
    public void sendAttachmentMailTest() throws MessagingException {
        mailService.sendAttachmentMail("lovesuperzc@163.com", "这是一封带附件的邮件", "I'm SuperZ!! This is my thesis", "D:\\download\\201577F0152_张超_基于ssm共享玩具租赁平台修改.doc");
    }

    @Test
    public void sendImgMailTest() throws MessagingException {
        String path = "C:\\Users\\lovez\\Pictures\\FLAMING MOUNTAIN.JPG";
        String rid = "img01";
        StringBuilder content = new StringBuilder();
        content.append("<html>")
                .append("<h1>").append("狗").append("</h1>")
                .append("<img src=\'cid:").append(rid).append("\'>")
                .append("</html>");
        mailService.sendImgMail("lovesuperzc@163.com", "这是一封带图片🐕 的邮件", content.toString(), path, rid);
    }

    @Test
    public void sendTemplateMailTest() throws MessagingException {
        Context context = new Context();
        context.setVariable("id", "17763");
        String content = templateEngine.process("mailTemplate001", context);
        mailService.sendHtmlMail("lovesuperzc@163.com", "这是一封带☞🕷的邮件", content);
    }
}
